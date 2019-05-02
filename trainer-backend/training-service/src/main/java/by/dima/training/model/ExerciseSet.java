package by.dima.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class ExerciseSet {
    private Integer id;
    private Integer count;
    private ExerciseRepeating repeating;

    public ExerciseSet(Integer id) {
        this.id = id;
    }
}
