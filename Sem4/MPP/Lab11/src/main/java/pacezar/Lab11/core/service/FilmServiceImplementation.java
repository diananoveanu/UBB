package pacezar.Lab11.core.service;

import pacezar.Lab11.core.model.Film;
import pacezar.Lab11.core.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImplementation implements FilmService {
    private static final Logger log = LoggerFactory.getLogger(
            FilmServiceImplementation.class);

    @Autowired
    private FilmRepository filmRepository;


    @Override
    public List<Film> findAll() {
        log.trace("findAll --- method entered");

        List<Film> films = filmRepository.findAll();

        log.trace("findAll: films={}", films);

        return films;
    }

    @Override
    @Transactional
    public Film updateFilm(Long filmId, String title,
            String director, Integer releaseYear) {
        log.trace("updateFilm");

        Optional<Film> film = filmRepository.findById(filmId);

        film.ifPresent(f -> {
            f.setTitle(title);
            f.setDirector(director);
            f.setReleaseYear(releaseYear);
        });

        log.trace("updateStudent: student={}", film.get());

        return film.orElse(null);
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {
        log.trace("delete: id={}", id);

        filmRepository.deleteById(id);

        log.trace("delete --- method finished");
    }
}
