package by.dima.goal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    private String name;

    @JsonProperty
    private Integer userId;

    @JsonProperty
    private Float goal;

    @JsonProperty
    private Date targetDate;

    private ProcessType processType;

    @JsonProperty
    @OneToMany(targetEntity = Progress.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id")
    private List<Progress> progress;

    @ManyToOne(targetEntity = BodyAttribute.class, fetch = FetchType.EAGER)
    private BodyAttribute bodyAttribute;
}
