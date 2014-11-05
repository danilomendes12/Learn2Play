package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Entity
public class User extends Model {

    @Id
    Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String nickname;

    @Constraints.Email
    private String email;

    @Constraints.Min(6)
    private String password;


    //Construtor
    public User(String name, String nickName, String email, String password){
        this.name = name;
        this.nickname = nickName;
        this.email = email;
        this.password = password;
    }


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //Metodos static
    public static User login(String email, String password) {
        List<User> users = find
                .select("name, nickname, email, password")
                .where()
                .eq("email", email)
                .eq("password", password)
                .findList();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public static void signup(String name, String nickname, String email, String password) {
        User user = new User(name, nickname, email, password);
        System.out.println(nickname);
        user.save();
    }

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );

    public static User getById(Long user_id) {
        return find.byId(user_id);
    }
}