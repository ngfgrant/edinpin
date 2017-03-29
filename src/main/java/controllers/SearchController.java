package controllers;

import com.google.inject.Singleton;
import ninja.Result;
import ninja.Results;

@Singleton
public class SearchController {
    public Result search() {

        return Results.html();

    }

    public Result map() {

        return Results.html();

    }
}
