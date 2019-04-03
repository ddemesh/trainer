package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PassedSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUser;
    private Date execDate;

    @Column(name = "id_set")
    private Integer exerciseSetId;

    @Transient
    @JsonInclude
    private ExerciseSet exerciseSet;

    @Column(name = "id_training")
    private Integer idTraining;
}
