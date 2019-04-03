package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(targetEntity = Muscle.class, fetch = FetchType.EAGER)
    @JoinTable(name = "muscle_exercise",
            joinColumns = {@JoinColumn(name = "id_exercise")},
            inverseJoinColumns = {@JoinColumn(name = "id_muscle")})
    private Set<Muscle> muscles;

    @OneToMany(targetEntity = ExerciseRepeating.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exercise")
    @JsonIgnore
    private Set<ExerciseRepeating> repeatings;
}
