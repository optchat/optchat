package controllers.user;

import models.service.user.UserService;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.user.create;

public class User extends Controller {

    @AddCSRFToken
    public static Result show() {
        return ok(create.render(Form.form(models.request.user.User.class)));
    }

    @RequireCSRFCheck
    public static Result create() {
        Form<models.request.user.User> userForm = Form.form(models.request.user.User.class).bindFromRequest();
        if (userForm.hasErrors()) {
            return badRequest(create.render(userForm));
        }

        // ユーザーエンティティとログインエンティティを保存
        String name = userForm.get().name;
        String mail = userForm.get().mail;
        String password = userForm.get().password;

        UserService.use().createUserEntity(name, mail);
        UserService.use().createLoginEntity(mail, password);

        return redirect(controllers.security.routes.Authentication.login());
    }
}
