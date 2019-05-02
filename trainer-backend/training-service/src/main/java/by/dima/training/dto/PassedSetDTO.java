package by.dima.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "passed_set")
public class PassedSetDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUser;
    private Date execDate;

    @Column(name = "id_set")
    private Integer exerciseSetId;

    @Column(name = "id_training")
    private Integer idTraining;

    public PassedSetDTO() {
    }
}
