package pacezar.Lab11.core.service;

import pacezar.Lab11.core.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Film updateFilm(Long filmId, String name, String director, Integer releaseYear);

    Film saveFilm(Film film);

    void deleteFilm(Long id);

}
