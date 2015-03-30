package models;


import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by danilomendes on 3/29/15.
 */
@Entity
public class Comentario {
    @Id
    Long id;

    @Constraints.Required
    private String title;
}
