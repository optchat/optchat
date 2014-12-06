package controllers.chat;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.chat.channels.connect;

@play.mvc.Security.Authenticated(models.utils.Secured.class)
public class Channels extends Controller {

    public static Result connect() {
        return ok(connect.render());
    }
}
