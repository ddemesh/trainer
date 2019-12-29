package by.dima.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(targetEntity = Muscle.class, fetch = FetchType.EAGER)
    @JoinTable(name = "muscle_exercise",
            joinColumns = {@JoinColumn(name = "id_exercise")},
            inverseJoinColumns = {@JoinColumn(name = "id_muscle")})
    @JsonProperty
    private Set<Muscle> muscles;

    @OneToMany(targetEntity = ExerciseRepeating.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exercise")
    @JsonIgnore
    private Set<ExerciseRepeating> repeatings;

    @Enumerated(EnumType.STRING)
    private LoadType type;
}
