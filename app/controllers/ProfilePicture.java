package controllers;

import com.mysql.jdbc.Blob;
import org.h2.store.fs.FileUtils;
import play.mvc.Controller;
import play.mvc.*;
import utils.*;

import views.html.*;
import models.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileStore;

/**
 * Created by danilomendes on 11/5/14.
 */
public class ProfilePicture extends Controller {

    public static Result upload() {

        Http.MultipartFormData body = ProfilePicture.request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");

        if (picture != null) {

            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();

            User user = User.getById(Long.valueOf(session("user_id")));
            try {
                user.setProfileImage(FileReader.readFile(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return redirect("/userpanel");
        } else {
            flash("error", "Missing file");
            return redirect("/userpanel");
        }
    }

    public static Result getProfilePicture() {

        User me = User.getById(Long.valueOf(session("user_id")));

        if (me == null) {
            return notFound();
        } else if (me.getProfileImage() != null) {
            return ok(me.getProfileImage());
        } else {
            return redirect("/assets/img/team/5.jpg");
        }
    }

}
