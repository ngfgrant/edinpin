
package conf;


import controllers.AdminController;
import controllers.MapController;
import controllers.SearchController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        // Home Routes
        router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/search").with(SearchController::search);


        // Admin Routes
        router.GET().route("/admin/home").with(AdminController::index);
        router.GET().route("/admin/login").with(AdminController::adminLogin);

        // Routes for resources
        router.GET().route("/admin/view-resources").with(AdminController::viewResources);
        router.GET().route("/admin/add-resource").with(AdminController::addResource);
        router.GET().route("/admin/delete-resource/{resourceId}").with(AdminController::deleteIndividualResource);
        router.GET().route("/admin/edit-resource/{resourceId}").with(AdminController::editResource);
        router.POST().route("/admin/add-resource").with(AdminController::addResourceForm);
        router.POST().route("/admin/edit-resource/{resourceId}").with(AdminController::editResourceForm);


        // User Routes
        router.GET().route("/admin/add-user").with(AdminController::addUser);
        router.GET().route("/admin/view-users").with(AdminController::viewUsers);
        router.GET().route("/admin/edit-user/{userId}").with(AdminController::editUser);
        router.GET().route("/admin/delete-user/{userId}").with(AdminController::deleteIndividualUser);
        router.POST().route("/admin/add-user").with(AdminController::addUserForm);
        router.POST().route("/admin/edit-user/{userId}").with(AdminController::editUserForm);


        // Map Routes
        router.GET().route("/map").with(MapController::map);
        router.GET().route("/map/test").with(MapController::testingGeoCoding);
        router.GET().route("/map/json-resources").with(MapController::jsonResources);
        //router.GET().route("/map/test/{address}").with(MapController::testingGeoCoding);


        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
