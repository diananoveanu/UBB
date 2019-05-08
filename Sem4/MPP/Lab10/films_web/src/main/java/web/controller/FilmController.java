package web.controller;

import core.model.Film;
import core.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.converter.FilmConverter;
import web.dto.FilmDto;


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


}
