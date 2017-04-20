package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import models.Resource;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

/**
 * Created by niallgrant on 16/04/17.
 */

@Singleton
public class MapController {

    String apiKey = "AIzaSyATxrziUqjkKd4dNfc58IccD6n-C0kC9hQ";
    GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    public Result map() {

        GeocodingResult[] results = new GeocodingResult[10];
        for (Resource tempResource : getListOfResources()) {
            try {
                results = GeocodingApi.geocode(context, tempResource.getAddress()).await();
            } catch (ApiException | InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

        return Results.html().render("coordinates", results).render("apiKey", apiKey);

    }

    public Result testingGeoCoding() {
        GeocodingResult[] results = new GeocodingResult[10];
        for (Resource tempResource : getListOfResources()) {
            try {
                results = GeocodingApi.geocode(context, tempResource.getAddress()).await();
            } catch (ApiException | InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        return Results.html().render("coordinates", results);
    }

    @UnitOfWork
    private List<Resource> getListOfResources() {
        EntityManager entityManager = entitiyManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM Resource x");
        return (List<Resource>) q.getResultList();
    }


}
