package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.security.login.login;

public class Application extends Controller {

    public static Result index() {
        return ok(login.render());
    }

}
