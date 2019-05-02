package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PassedSet {
    private Integer id;
    private Integer idUser;
    private Date execDate;
//    private Integer exerciseSetId;
    private ExerciseSet exerciseSet;
    @JsonIgnore
    private Training training;
}
