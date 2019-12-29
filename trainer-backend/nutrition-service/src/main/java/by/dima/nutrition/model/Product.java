package by.dima.nutrition.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Float protein;
    private Float fat;
    private Float carbohydrate;

    @OneToMany
    @JoinColumn(name = "product_id")
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PUBLIC)
    private Set<Portion> portions;
}
