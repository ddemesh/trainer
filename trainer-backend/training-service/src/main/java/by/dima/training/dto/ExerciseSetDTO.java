package by.dima.training.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ExerciseSetDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_repeating")
    private ExerciseRepeatingDTO repeating;
}
