package pacezar.Lab11.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pacezar.Lab11.core.model.Customer;

import java.util.Set;

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
    public Set<Customer> customers;
//
//    public FilmDto(String title, String director, Integer releaseYear){
//        this.title = title;
//        this.director = director;
//        this.releaseYear = releaseYear;
//    }

    @Override
    public String toString() {
        return "FilmDto{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                "} " + super.toString();
    }
}
