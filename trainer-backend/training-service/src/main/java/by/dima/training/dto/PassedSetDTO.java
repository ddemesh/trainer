package by.dima.training.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PassedSetDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUser;
    private Date execDate;

    @Column(name = "id_set")
    private Integer exerciseSetId;
//
//    @Transient
//    @JsonInclude
//    private ExerciseSetDTO exerciseSetDTO;

    @Column(name = "id_training")
    private Integer idTraining;
}
