package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Resource;
import models.ResourceType;
import models.Subscription;
import models.User;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;



/**
 * Created by User on 17/04/2017.
 */
@Singleton
public class NotificationController {
    @Inject
    Provider<EntityManager> entitiyManagerProvider;
    static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Transactional
    public Result index(){
        // Instantiate the local entityManager
        EntityManager entityManager = entitiyManagerProvider.get();
        // Compose query string using JPA Query Language (http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html)
        Query resourceTypes = entityManager.createQuery("SELECT x FROM ResourceType x");
        // Save the results of query to a list
        List<ResourceType> resourceTypeList = (List<ResourceType>) resourceTypes.getResultList();

        // Compose query string using JPA Query Language (http://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html)
        Query users = entityManager.createQuery("SELECT x FROM User x");
        // Save the results of query to a list
        List<User> usersList = (List<User>) users.getResultList();
        return Results.html().render("resourceTypes", ResourceController.allResourceTypes).render("users", usersList);
    }
    //send sms method

    @UnitOfWork
    public Result indexPost (@Param("userName") String userName, @Param("resourceType") String resourceType) {
        EntityManager entityManager = entitiyManagerProvider.get();

        Query findUser = entityManager.createQuery("SELECT x FROM User x WHERE x.name = :name").setParameter("name", userName);
       // Query findResourceType = entityManager.createQuery("SELECT x FROM ResourceType x WHERE x.name = :name").setParameter("name", resourceType);
        User tempUser = (User) findUser.getSingleResult();

        logger.info(tempUser.getName() + "  1");
       // ResourceType tempResourceType= (ResourceType) findResourceType.getSingleResult();

       // logger.info(tempResourceType.getName() + "  2");
       // Subscription newSubscription = new Subscription(tempUser, tempResourceType );

        for (ResourceType tempResourceType : ResourceController.allResourceTypes){
            if(tempResourceType.getName().equals(resourceType)){
                logger.info("found a match adding observer?");
                tempResourceType.addObserver(tempUser);
                logger.info("size of observer for resource = " + tempResourceType.countObservers());
            }
        }

        return Results.html();
    }



}
