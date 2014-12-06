package controllers.user;

import models.request.security.Login;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.security.authentication.login;

public class User extends Controller {

    @AddCSRFToken
    public static Result show() {
        return ok(login.render(Form.form(Login.class)));
    }

    @RequireCSRFCheck
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
