package by.dima.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class TrainingComplex {
    private Integer id;
    private String name;
    private Set<Training> training;
    private Set<Tag> tags;

    public TrainingComplex(String name, Set<Training> training, Set<Tag> tags) {
        this.name = name;
        this.training = training;
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingComplex that = (TrainingComplex) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(training, that.training) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
