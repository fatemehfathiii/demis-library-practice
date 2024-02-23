package ir.fathi.library.dao;

import ir.fathi.library.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookDao{
    Long save(Book book);
    Collection<Book> getAllBook();
    Optional<Book> getBookById(Long id);
}
