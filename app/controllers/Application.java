package controllers;

import play.mvc.*;

import views.html.*;
import models.User;

public class Application extends Controller {

    public static Result index() {
        String title = "Learn2Play";
        return ok(index.render(title));
    }

    public static Result signupPage() {
        String title = "Learn2Play - Sign Up";
        return ok(page_registration.render(title));
    }

    public static Result loginPage() {
        String title = "Learn2Play - Login";
        return ok(page_login.render(title));
    }

    public static Result userIndex() {
        String title = "Learn2Play - Welcome";

        if (session("user_id") == null) {
            return redirect("/");
        } else {

            User me = User.getById(Long.valueOf(session("user_id")));
            if (me == null) {
                return redirect("/");
            } else {
                return ok(user_index.render(title, me));
            }
        }
    }

}
