package controllers.security;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.security.authentication.login;

public class Authentication extends Controller {

    public static Result login() {
        return ok(login.render());
    }
    public static Result logout() {
        return ok(login.render());
    }
}
