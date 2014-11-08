package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import utils.UserSignupValidation;
import views.html.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by danilomendes on 11/5/14.
 */
public class UserActivity extends Controller {

    public static Result signup(String name, String nickname, String email, String password, String confirmationPassword) {

        UserSignupValidation error = UserSignupValidation.validate(name, nickname, email, password, confirmationPassword);

        if(error == null) {

            User.signup(name, nickname, email, password);

            User me = User.login(email, password);

            if (me == null) {
                return notFound();
            } else {
                session("user_id", me.getId().toString());
                return redirect("/userindex");
            }

        } else {

            return redirect("/signup?error=".concat(error.getCode().toString()).concat("&name=").concat(name).concat("&nickname=").concat(nickname).concat("&email=").concat(email));
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
