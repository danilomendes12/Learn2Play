package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by danilomendes on 11/5/14.
 */
public class UserActivity extends Controller {

    public static Result signup(String name, String nickname, String email, String password) {
        System.out.println("Teste "+nickname);
        User.signup(name, nickname, email, password);

        User me = User.login(email, password);

        if (me == null) {
            return notFound();
        } else {
            session("user_id", me.getId().toString());
            return redirect("/userindex");
        }
    }

    public static Result login(String email, String password) {

        User me = User.login(email, password);

        if (me == null) {
            return notFound();
        } else {
            session("user_id", me.getId().toString());
            return redirect("/userindex");
        }
    }

}
