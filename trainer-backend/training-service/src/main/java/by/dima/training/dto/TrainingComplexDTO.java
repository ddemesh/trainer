package by.dima.training.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class TrainingComplexDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_complex")
    private Set<TrainingDTO> trainingDTO;

    @ManyToMany(targetEntity = MuscleDTO.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tag_complex",
            joinColumns = {@JoinColumn(name = "id_complex")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")})
    private Set<TagDTO> tagDTOS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingComplexDTO that = (TrainingComplexDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(trainingDTO, that.trainingDTO) &&
                Objects.equals(tagDTOS, that.tagDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
