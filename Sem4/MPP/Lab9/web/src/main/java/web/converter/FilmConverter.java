package web.converter;

import core.model.Film;
import org.springframework.stereotype.Component;
import web.dto.FilmDto;

@Component
public class FilmConverter extends BaseConverter<Film, FilmDto> {
    @Override
    public Film convertDtoToModel(FilmDto dto) {
        Film film = Film.builder()
                .name(dto.getName())
                .director(dto.getDirector())
                .duration(dto.getDuration())
                .rating(dto.getRating())
                .releaseYear(dto.getReleaseYear())
                .score(dto.getScore())
                .build();
        film.setId(dto.getId());
        return film;
    }

    @Override
    public FilmDto convertModelToDto(Film film) {
        FilmDto dto = FilmDto.builder()
                .name(film.getName())
                .director(film.getDirector())
                .duration(film.getDuration())
                .rating(film.getRating())
                .releaseYear(film.getReleaseYear())
                .score(film.getScore())
                .build();
        dto.setId(film.getId());
        return dto;
    }
}
