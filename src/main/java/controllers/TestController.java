package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.Test;
import ninja.Result;
import ninja.Results;
import ninja.ReverseRouter;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import com.google.inject.persist.Transactional;
/**
 * Created by User on 28/03/2017.
 */
public class TestController {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    @Inject
    ReverseRouter router;

    @UnitOfWork
    public Result getIndex() {

        EntityManager entityManager = entitiyManagerProvider.get();
        Test test1 = new Test();
        test1.setEmail("abc");
        Query q = entityManager.createQuery("SELECT email FROM Test x");
        String email =  q.getFirstResult();
        //String postRoute = router.with(TestController::postIndex).build();

        return Results
                .html()
                .render("TestList", );
               // .render("postRoute", postRoute);


    }

    @Transactional
    public Result postIndex(Test test) {
        EntityManager entityManager = entitiyManagerProvider.get();
        entityManager.persist(test);
        return Results.redirect(router.with(TestController::getIndex).build());
    }

}
