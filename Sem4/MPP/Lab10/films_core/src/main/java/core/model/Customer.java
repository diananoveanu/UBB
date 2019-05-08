package core.model;

import lombok.*;

import javax.persistence.Entity;

/**
 * author: radu
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity<Long> {
    private String name;

    @Override
    public String toString() {
        return "Customer{" +
               "title='" + name + '\'' +
               "} " + super.toString();
    }
}
