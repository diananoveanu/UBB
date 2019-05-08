package web.dto;

import lombok.*;

/**
 * @author diananoveanu
 */

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class FilmDto extends BaseDto {
    private String name;
    private String director;
    private String rating;
    private Integer duration;
    private Double score;
    private Integer releaseYear;
}

