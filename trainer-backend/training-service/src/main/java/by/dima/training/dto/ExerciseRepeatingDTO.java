package by.dima.training.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class ExerciseRepeatingDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer complexity;
    private Integer count;
    private Integer rest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exercise")
    private ExerciseDTO exerciseDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRepeatingDTO that = (ExerciseRepeatingDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(complexity, that.complexity) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
