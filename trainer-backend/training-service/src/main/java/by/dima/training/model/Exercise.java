package by.dima.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Exercise {
    private Integer id;
    private String name;
    private Set<Muscle> muscles;
}
