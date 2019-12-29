package by.dima.nutrition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name="user_meal",
            joinColumns=@JoinColumn(name="id_meal")
    )
    @Column(name="id_user")
    @JsonIgnore
    private Set<Integer> users;
}
