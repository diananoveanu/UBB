package core.service;

import core.model.Film;
import core.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImplementation implements FilmService {
    private static final Logger log = LoggerFactory.getLogger(
            FilmServiceImplementation.class);

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> getAllFilms() {
        log.trace("getAllFilms --- method entered");

        List<Film> result = filmRepository.findAll();

        log.trace("getAllFilms: result={}", result);

        return result;
    }

    @Override
    public Film saveFilm(Film film) {
        log.trace("saveFilm: film={}", film);

        filmRepository.save(film);

        log.trace("saveFilm --- method finished");
        Film savedFilm = this.filmRepository.save(film);
        //todo log result...

        return savedFilm;
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        log.trace("update --method entered id={} entity={}", id, film);

        Optional<Film> opt = filmRepository.findById(id);
        if ( opt.isPresent() ) {
            this.saveFilm(film);
            log.trace("update --method finished");
            return film;
        }

        log.trace("update --method finished");
        return null;

    }

    @Override
    public void deleteFilm(Long id) {
        log.trace("delete: id={}", id);

        filmRepository.deleteById(id);

        log.trace("delete --- method finished");
    }

    @Override
    public Optional<Film> findById(Long id) {

        log.trace("findById --method entered id = {}", id);

        return filmRepository.findById(id);
    }
}