package models.utils;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Securityヘルパー
 * Created by s1hit on 2014/12/06.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("userId");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        String returnUrl = ctx.request().uri();
        if (returnUrl == null) {
            returnUrl = "/";
        }
        ctx.session().put("returnUrl", returnUrl);
        return redirect(controllers.security.routes.Authentication.login());
    }
}