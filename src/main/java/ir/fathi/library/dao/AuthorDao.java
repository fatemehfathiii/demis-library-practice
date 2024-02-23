package ir.fathi.library.dao;

import ir.fathi.library.model.Author;
import ir.fathi.library.model.Publisher;

import java.util.Optional;

public interface AuthorDao {
    Long saveAuthorDao(String fullName);
    Optional<Author> getAuthorByFullName(String fullName);
    Optional<Author> getAuthorById(Long bookId);
}
