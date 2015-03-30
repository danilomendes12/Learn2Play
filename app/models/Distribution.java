package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danilomendes on 3/30/15.
 */
@Entity
public class Distribution {
    @Id
    Long id;

    @ManyToOne
    Game game;

    @ManyToOne
    Plataforma console;

    @OneToMany
    List<Tutorial> tutorials;
}
