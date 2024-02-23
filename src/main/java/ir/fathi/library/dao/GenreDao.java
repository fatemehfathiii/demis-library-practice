package ir.fathi.library.dao;

import ir.fathi.library.model.Author;
import ir.fathi.library.model.Genre;

import java.util.Optional;

public interface GenreDao {
    Long saveGenreDao(String title);
    Optional<Genre> getGenreById(Long id);
}
