package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PassedSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @JsonProperty
    private Integer idUser;
    @JsonProperty
    private Date execDate;

    @Column(name = "id_set")
    @JsonProperty
    private Integer exerciseSet;

    @Transient
    @JsonInclude
    private ExerciseSet set;

    @Column(name = "id_training")
    @JsonProperty
    private Integer idTraining;
}
