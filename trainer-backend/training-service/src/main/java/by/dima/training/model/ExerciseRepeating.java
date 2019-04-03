package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class ExerciseRepeating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer complexity;
    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exercise")
    private Exercise exercise;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_repeating")
    @JsonIgnore
    private Set<ExerciseSet> sets;

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
