package by.dima.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training")
public class TrainingDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer weekDay;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_training")
    private Set<PassedSetDTO> passedSetDTOS;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_complex")
    private TrainingComplexDTO trainingComplexDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDTO trainingDTO = (TrainingDTO) o;
        return Objects.equals(id, trainingDTO.id) &&
                Objects.equals(weekDay, trainingDTO.weekDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}