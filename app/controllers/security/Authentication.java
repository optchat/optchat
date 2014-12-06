package controllers.security;

import models.request.security.Login;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import views.html.security.authentication.login;

public class Authentication extends Controller {

    @AddCSRFToken
    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result logout() {
        return ok(login.render(Form.form(Login.class)));
    }

    @RequireCSRFCheck
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        String returnUrl = getReturnUrl();

        session().clear();
        session("userId", Long.toString(loginForm.get().user.userId));

        return redirect(returnUrl);
    }

    /**
     * ログイン成功後の遷移先URLを取得する
     * @return 遷移先URL
     */
    private static String getReturnUrl() {
        // Securityヘルパーで退避した遷移先URLを取得
        String returnUrl = session("returnUrl");
        if (returnUrl == null || returnUrl.equals("") ||
                returnUrl.equals(controllers.chat.routes.Channels.connect().absoluteURL(request()))) {

            // 遷移先URLを取得できない場合は、遷移先をチャットルームに設定
            returnUrl = controllers.chat.routes.Channels.connect().url();
        }
        return returnUrl;
    }

}
