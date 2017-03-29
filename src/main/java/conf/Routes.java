
package conf;


import controllers.AdminController;
import controllers.SearchController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        

        router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/search").with(SearchController::search);
        router.GET().route("/map").with(SearchController::map);
        router.GET().route("/admin/home").with(AdminController::index);
        router.GET().route("/admin/login").with(AdminController::adminLogin);
        router.GET().route("/admin/edit-resource").with(AdminController::editResource);
        router.GET().route("/admin/add-resource").with(AdminController::addResource);
        router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        
 
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
