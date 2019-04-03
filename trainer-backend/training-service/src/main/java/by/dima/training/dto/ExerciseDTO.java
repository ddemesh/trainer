package by.dima.training.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class ExerciseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(targetEntity = MuscleDTO.class, fetch = FetchType.EAGER)
    @JoinTable(name = "muscle_exercise",
            joinColumns = {@JoinColumn(name = "id_exercise")},
            inverseJoinColumns = {@JoinColumn(name = "id_muscle")})
    private Set<MuscleDTO> muscleDTOs;
}
