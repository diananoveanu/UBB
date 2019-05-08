package core.model;


import lombok.*;

import javax.persistence.Entity;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Customer extends BaseEntity<Long> {
    private String name;
}
