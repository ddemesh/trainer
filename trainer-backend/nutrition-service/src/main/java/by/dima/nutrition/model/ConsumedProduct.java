package by.dima.nutrition.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ConsumedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty
    private Integer userId;

    @ManyToOne(targetEntity = Product.class)
    private Product product;
    private Integer size;
    @JsonProperty
    private Date date;
}
