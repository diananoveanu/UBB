package web.controller;

import core.model.Film;
import core.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.FilmConverter;
import web.dto.FilmDto;
import web.dto.FilmsDto;

import java.util.List;


/**
 * @author diananoveanu
 */

@RestController
public class FilmController {
    private static final Logger log =
            LoggerFactory.getLogger(FilmController.class);
    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmConverter filmConverter;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    FilmsDto getAllFilms() {
        log.trace("getAllFilms --- method entered");

        List<Film> films = filmService.getAllFilms();
        List<FilmDto> dtos = filmConverter.convertModelsToDtos(films);
        FilmsDto result = new FilmsDto(dtos);

        log.trace("getAllFilms: result={}", result);

        return result;

    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    FilmDto saveFilm(@RequestBody FilmDto dto) {
        log.trace("saveFilm: web_folder.dto={}", dto);

        Film saved = this.filmService.saveFilm(
                filmConverter.convertDtoToModel(dto)
        );

        FilmDto result = filmConverter.convertModelToDto(saved);

        log.trace("saveFilm: result={}", result);

        return result;

    }


    @RequestMapping(value = "/films/{id}", method = RequestMethod.PUT)
    FilmDto updateFilm(@PathVariable Long id,
                       @RequestBody FilmDto dto) {
        log.trace("updateFilm: id={},web_folder.dto={}", id, dto);

        Film update = filmService.updateFilm(id,
                filmConverter.convertDtoToModel(dto));
        FilmDto result = filmConverter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        log.trace("deleteFilm: id={}", id);

        filmService.deleteFilm(id);

        log.trace("deleteFilm --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    FilmDto findFilm(@PathVariable Long id) {
        log.trace("findFilm --method entered id = {}", id);

        if (filmService.findById(id).isEmpty())
            return new FilmDto();

        Film film = filmService.findById(id).get();
        FilmDto filmDto = filmConverter.convertModelToDto(film);

        log.trace("findFilm --method exit res = {}", filmDto);

        return filmDto;
    }
}