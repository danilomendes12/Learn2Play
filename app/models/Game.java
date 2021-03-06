package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danilomendes on 11/5/14.
 */
@Entity
public class Game extends Model{

    @Id
    Long id;

    @Constraints.Required
    private String name;

    @OneToMany
    List<Distribution> distributions;
}
