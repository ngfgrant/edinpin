package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.Test;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 28/03/2017.
 */
public class TestController {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    @UnitOfWork
    public Result getIndex() {

        EntityManager entityManager = entitiyManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM GuestbookEntry x");
        List<Test> guestbookEntries = (List<Test>) q.getResultList();


        return Results
                .html();


    }
}
