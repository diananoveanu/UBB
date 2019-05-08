package pacezar.Lab11.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pacezar.Lab11.core.model.Film;
import pacezar.Lab11.web.dto.FilmDto;


/**
 * @author diananoveanu
 */

@Component
public class FilmConverter extends BaseConverter<Film, FilmDto> {

    private static final Logger log = LoggerFactory.getLogger(
            FilmConverter.class);

    @Override
    public Film convertDtoToModel(FilmDto dto) {
        Film film = new Film(dto.getTitle(), dto.getDirector(),dto.getReleaseYear(),dto.getCustomers());
        film.setId(dto.getId());
        return film;
    }

    @Override
    public FilmDto convertModelToDto(Film film) {
        FilmDto filmDto = new FilmDto(film.getTitle(),
                film.getDirector(),
                film.getReleaseYear(),
                film.getCustomers());
        filmDto.setId(film.getId());
        return filmDto;
    }
}
