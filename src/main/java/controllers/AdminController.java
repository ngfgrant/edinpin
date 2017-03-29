package controllers;

import com.google.inject.Singleton;
import ninja.Result;
import ninja.Results;

/**
 * Created by User on 28/03/2017.
 */

@Singleton
public class AdminController {
    public Result index() {

        return Results.html();

    }

    public Result adminLogin() {

        return Results.html();

    }

    public Result editResource() {

        return Results.html();

    }

    public Result addResource() {

        return Results.html();

    }
}
