package core.service;

import core.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> getAllFilms();

    Film saveFilm(Film film);

    Film updateFilm(Long id, Film film);
    Optional<Film> findById(Long id);

    void deleteFilm(Long id);
}
