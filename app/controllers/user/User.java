package controllers.user;

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
        models.entity.User user = userForm.get().user;
        models.entity.Login login = userForm.get().login;
        user.save();
        login.save();

        return redirect(controllers.security.routes.Authentication.login());
    }
}
