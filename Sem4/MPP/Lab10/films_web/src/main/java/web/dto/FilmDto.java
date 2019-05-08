package web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author diananoveanu
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmDto extends BaseDto {
    private String title;
    private String director;
    private Integer releaseYear;


    @Override
    public String toString() {
        return "FilmDto{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                "} " + super.toString();
    }
}
