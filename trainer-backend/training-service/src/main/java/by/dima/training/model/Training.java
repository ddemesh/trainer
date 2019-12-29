package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weekDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_complex")
    @JsonIgnore
    private TrainingComplex complex;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_training", updatable = false)
    private List<PassedSet> passedSets;

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