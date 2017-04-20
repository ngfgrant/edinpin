package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Resource;
import models.ResourceType;
import models.Subscription;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19/04/2017.
 */
@Singleton
public class ResourceController {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    private static ResourceType health = new ResourceType("health");
    private static ResourceType food = new ResourceType("food");
    private static ResourceType shelter = new ResourceType("shelter");
    public static List<ResourceType> allResourceTypes = new ArrayList<>();
    @Transactional
    public Result setUpResources() {
        //EntityManager entityManager = entitiyManagerProvider.get();
       // entityManager.persist(health);
       // entityManager.persist(food);
       // entityManager.persist(shelter);
        allResourceTypes.add(health);
        allResourceTypes.add(food);
        allResourceTypes.add(shelter);
        return Results.redirect("/");
    }

    @Transactional
    public Result addResource() {
        EntityManager entityManager = entitiyManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM ResourceType x");
        //List<ResourceType> allResourceTypes = q.getResultList();
        return Results.html().render("resourceTypes", allResourceTypes);
    }

    /*
    * @Transactional automatically opens and closes transactions around the annotated method. I.e. saving and deleting.
    */
    @Transactional
    public Result deleteIndividualResource(@PathParam("resourceId") String resourceId) {
        // instantiate the local entityManager
        EntityManager entityManager = entitiyManagerProvider.get();
        // Parse the id which entered as a String into Long
        Long idInt = Long.parseLong(resourceId);
        // Compose query string using JPA Query Language (http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html)
        Query q = entityManager.createQuery("SELECT x FROM Resource x WHERE x.id = :id").setParameter("id", idInt);
        // Use Entity Manager to perform action on the selected object (in this case delete).
        entityManager.remove(q.getSingleResult());

        return Results.redirect("/admin/view-resources");
    }

    @UnitOfWork
    public Result editResource(@PathParam("resourceId") String resourceId) {
        EntityManager entityManager = entitiyManagerProvider.get();
        Long idInt = Long.parseLong(resourceId);
        Query resourceQuery = entityManager.createQuery("SELECT x FROM Resource x WHERE x.id = :id").setParameter("id", idInt);
        Query resourceTypeQuery = entityManager.createQuery("SELECT x FROM ResourceType x");
        //List<ResourceType> allResourceTypes = (List<ResourceType>) resourceTypeQuery.getResultList();

        return Results.html().render("resource", resourceQuery.getSingleResult()).render("resourceTypes", allResourceTypes);
    }

    @Transactional
    public Result editResourceForm(Resource resource) {
        EntityManager entityManager = entitiyManagerProvider.get();
        Resource tempResource = entityManager.find(Resource.class, resource.getResourceId());
        tempResource.setAddress(resource.getAddress());
        tempResource.setCompanyName(resource.getCompanyName());
        tempResource.setDate(resource.getDate());
        tempResource.setDescription(resource.getDescription());
        tempResource.setManagerName(resource.getManagerName());
        tempResource.setPostCode(resource.getPostCode());
        tempResource.setResourceTitle(resource.getResourceTitle());
        tempResource.setResourceType(resource.getResourceType());
        return Results.redirect("/admin/view-resources");
    }

    /*
     * JPA has to open connections, save data, maintain caches - and you’d possibly go crazy if you’d have to manage that for each controller method yourself.
     * This is what @UnitOfWork is for. Simply annotate your method with that annotation and Guice Persists will handle all the boilerplate for you.
     * @UnitOfWork only handles connections and does not help you with transactions.
     */
    @UnitOfWork
    public Result viewResources() {
        // Instantiate the local entityManager
        EntityManager entityManager = entitiyManagerProvider.get();
        // Compose query string using JPA Query Language (http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html)
        Query q = entityManager.createQuery("SELECT x FROM Resource x");
        // Save the results of query to a list
        List<Resource> resourcesList = (List<Resource>) q.getResultList();
        // Return the list to the view
        return Results.html().render("resources", resourcesList);
    }

    @Transactional
    public Result addResourceForm(Resource resource) {
        EntityManager entityManager = entitiyManagerProvider.get();
        entityManager.persist(resource);
        //Query resourceTypesQuery = entityManager.createQuery("SELECT x FROM ResourceType x WHERE x.name = :name").setParameter("name", resource.getResourceType());
        //ResourceType resourceType = (ResourceType) resourceTypesQuery.getSingleResult();

        for(ResourceType tempType : allResourceTypes){
            if(resource.getResourceType().equals(tempType.getName())){
                tempType.notifySubscribers();
            }
        }

        Query subscriptionListQuery = entityManager.createQuery("SELECT x FROM Subscription x");
        // Save the results of query to a list
        List<Subscription> subscriptionList = (List<Subscription>) subscriptionListQuery.getResultList();
        return Results.redirect("/admin/view-resources");
    }

}
