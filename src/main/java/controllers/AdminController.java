package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Resource;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 28/03/2017.
 */

@Singleton
public class AdminController {
    /*
     * Using @Inject annotation which lets us use "Dependency Injection" pattern in our Controllers. Read https://en.wikipedia.org/wiki/Dependency_injection and
     * http://stackoverflow.com/questions/130794/what-is-dependency-injection for more information about Dependency Injection. This is a more advanced topic, but good to know.
     * The EntityManager Provider give us access to persistence methods, much like the EntityFramework in ASP.NET. The entity manager is the key component that allows you to
     * update / save and query data based on your models.
     */
    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    public Result adminLogin() {
        return Results.html();
    }

    public Result addUser() {
        return Results.html();
    }

    public Result addResource() {
        return Results.html();
    }

    @UnitOfWork
    public Result index() {
        // Instantiate the local entityManager
        EntityManager entityManager = entitiyManagerProvider.get();
        // Compose query string using JPA Query Language (http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html)
        Query q = entityManager.createQuery("SELECT x FROM Resource x");
        // Save the results of query to a list
        List<Resource> resourcesList = (List<Resource>) q.getResultList();
        // Return the list to the view
        return Results.html().render("resources", resourcesList);
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
        Query q = entityManager.createQuery("SELECT x FROM Resource x WHERE x.id = :id").setParameter("id", idInt);
        return Results.html().render("resource", q.getSingleResult());
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
        return Results.redirect("/admin/view-resources");
    }

    /*
    Users
     */

    @UnitOfWork
    public Result editUser(@PathParam("userId") String userId) {
        EntityManager entityManager = entitiyManagerProvider.get();
        Long idInt = Long.parseLong(userId);
        Query q = entityManager.createQuery("SELECT x FROM User x WHERE x.id = :id").setParameter("id", idInt);
        return Results.html().render("user", q.getSingleResult());
    }

    @UnitOfWork
    public Result viewUsers() {
        EntityManager entityManager = entitiyManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM User x");
        List<User> userList = (List<User>) q.getResultList();
        return Results.html().render("users", userList);
    }

    @Transactional
    public Result deleteIndividualUser(@PathParam("userId") String userId) {
        EntityManager entityManager = entitiyManagerProvider.get();
        Long idInt = Long.parseLong(userId);
        Query q = entityManager.createQuery("SELECT x FROM User x WHERE x.id = :id").setParameter("id", idInt);
        entityManager.remove(q.getSingleResult());
        return Results.redirect("/admin/view-users");
    }

    @Transactional
    public Result addUserForm(User user) {
        EntityManager entityManager = entitiyManagerProvider.get();
        entityManager.persist(user);
        return Results.redirect("/admin/view-users");
    }

    @Transactional
    public Result editUserForm(User user) {
        EntityManager entityManager = entitiyManagerProvider.get();
        User tempUser = entityManager.find(User.class, user.getId());
        tempUser.setName(user.getName());
        tempUser.setPhoneNumber(user.getPhoneNumber());
        return Results.redirect("/admin/view-users");
    }

}


