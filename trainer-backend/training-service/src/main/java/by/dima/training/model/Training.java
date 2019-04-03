package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_complex")
    @JsonIgnore
    private TrainingComplex complex;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_training")
    private Set<PassedSet> passedSets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id) &&
                Objects.equals(rest, training.rest) &&
                Objects.equals(complex, training.complex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}