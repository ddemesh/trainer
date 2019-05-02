package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class Training {
    private Integer id;
    private Integer weekDay;
    @JsonIgnore
    private TrainingComplex complex;
    private Set<PassedSet> passedSets;

    public Training(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id) &&
                Objects.equals(weekDay, training.weekDay) &&
                Objects.equals(complex, training.complex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}