package by.dima.training.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;
    private Integer rest;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_repeating")
    private ExerciseRepeating repeating;
}
