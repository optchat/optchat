package controllers.chat;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.chat.channels.connect;

public class Channels extends Controller {

    public static Result connect() {
        return ok(connect.render());
    }
}
