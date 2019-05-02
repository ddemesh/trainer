package by.dima.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ExerciseRepeating {
    private Integer id;
    private Integer complexity;
    private Integer count;
    private Integer rest;
    private Exercise exercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRepeating that = (ExerciseRepeating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(complexity, that.complexity) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
