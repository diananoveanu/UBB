package pacezar.Lab11.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity<Long>{
    private Long filmId;
    private Long customerId;

    @Override
    public String toString() {
        return "Rental{" +
                "filmId='" + filmId + '\'' +
                "customerId='" + customerId + '\'' +
                "} " + super.toString();
    }
}

