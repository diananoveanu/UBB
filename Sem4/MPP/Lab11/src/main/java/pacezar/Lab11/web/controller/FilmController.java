package pacezar.Lab11.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pacezar.Lab11.core.model.Film;
import pacezar.Lab11.core.service.FilmService;
import pacezar.Lab11.web.converter.FilmConverter;
import pacezar.Lab11.web.dto.FilmDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author diananoveanu
 */

@RestController
public class FilmController {

    private static final Logger log = LoggerFactory.getLogger(
            FilmController.class);

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmConverter filmConverter;


    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public List<FilmDto> getFilms() {
        log.trace("getFilms");

        List<Film> students = filmService.findAll();

        log.trace("getFilm: students={}", students);

        return new ArrayList<>(filmConverter.convertModelsToDtos(students));
    }

    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.PUT)
    public FilmDto updateFilm(
            @PathVariable final Long filmId,
            @RequestBody final FilmDto filmDto) {


        Film student = filmService.updateFilm(filmId, filmDto.getTitle(),
                filmDto.getDirector(),
                filmDto.getReleaseYear());

        FilmDto result = filmConverter.convertModelToDto(student);

        log.trace("updateFilm: result={}", result);

        return result;
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    FilmDto saveFilm(@RequestBody FilmDto dto) {
        return filmConverter.convertModelToDto(
                filmService.saveFilm(
                        filmConverter.convertDtoToModel(dto)
                ));
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.DELETE)
    public void deleteFilm(@PathVariable Long id) {
        log.trace("deleteFilm: id={}", id);

        filmService.deleteFilm(id);

        log.trace("deleteFilm --- method finished");
    }
}
