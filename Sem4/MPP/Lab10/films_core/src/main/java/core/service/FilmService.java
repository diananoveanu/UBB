package core.service;

import core.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Film updateFilm(Long filmId, String name, String director, Integer releaseYear);

    Film saveFilm(Film film);

}
