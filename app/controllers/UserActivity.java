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

        if (!User.authenticate(email, password)) {
            String title = "Learn2Play - Login";
            return redirect("/loginerrmsg");
        } else {
            User me = User.login(email, password);
            session("user_id", me.getId().toString());
            return redirect("/userindex");
        }
    }

    public static Result logout(){
        session().remove("user_id");
        return redirect("/");
    }

    public static Result deleteAccount() {

        User user = User.getById(Long.valueOf(session("user_id")));

        if (user != null) {
            User.deleteAccount(user);
            return redirect("/api/logout");
        } else {
            return notFound();
        }
    }

    public static class Login {

        public String email;
        public String password;

    }

}
