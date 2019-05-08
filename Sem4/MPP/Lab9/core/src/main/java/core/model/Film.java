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
public class Film extends BaseEntity<Long> {
    private String name;
    private Integer releaseYear;
    private String director;
    private Integer duration;
    private Double score;
    private String rating;
}
