package core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Film extends BaseEntity<Long> {
    private String title;
    private String director;
    private Integer releaseYear;

    @Override
    public String toString() {
        return "Film{" +
                "name='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                "} " + super.toString();
    }
}
