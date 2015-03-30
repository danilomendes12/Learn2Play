package models;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danilomendes on 11/5/14.
 */
@Entity
public class Tutorial extends Model {

    @Id
    Long id;

    @Constraints.Required
    private String text;

    @Lob
    public byte[] tutorialImage;

    private int recomendationNumber;

    @ManyToOne
    User user;

    @OneToMany
    List<Comentario> comentarios;

    @ManyToOne
    Distribution distribution;


}
