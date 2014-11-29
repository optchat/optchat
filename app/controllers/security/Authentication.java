package controllers.security;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Authentication extends Controller {

    public static Result login() {
        return ok(login.render());
    }
    public static Result logout() {
        return ok(logout.render());
    }
}
