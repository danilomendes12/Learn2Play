package controllers;

import play.mvc.*;

import utils.UserSignupValidation;
import views.html.*;
import models.User;

public class Application extends Controller {

    public static Result index() {
        String title = "Learn2Play";
        return ok(index.render(title));
    }

    public static Result signupPage(Integer error, String name, String nickname, String email) {
        String title = "Learn2Play - Sign Up";

        UserSignupValidation userSignupValidation = UserSignupValidation.fromCode(error);

        if(userSignupValidation != null) {
            return ok(page_registration.render(title, userSignupValidation, name, nickname, email));
        } else {
            return ok(page_registration.render(title, userSignupValidation, "", "", ""));
        }
    }

    public static Result loginPage() {
        String title = "Learn2Play - Login";
        return ok(page_login.render(title, false));
   }

    public static Result loginPageErrMsg() {
        String title = "Learn2Play - Login";
        return ok(page_login.render(title, true));
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

    public static Result userPanel() {
        String title = "Learn2Play - User Panel";

        if (session("user_id") == null) {
            return redirect("/");
        } else {
            User me = User.getById(Long.valueOf(session("user_id")));
            if (me == null) {
                return redirect("/");
            } else {
                return ok(user_panel.render(title, me));
            }
        }
    }

    public static Result tutorialPage() {
        String title = "Tutorial";
        return ok(tutorial_page.render(title));
    }

}
