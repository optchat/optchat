package controllers.security;

import models.request.security.Login;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.security.authentication.login;

public class Authentication extends Controller {

    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result logout() {
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        session().clear();
        session("userId", Long.toString(loginForm.get().user.userId));
        return redirect(routes.Authentication.login());
    }

}
