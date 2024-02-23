package ir.fathi.library.dao.impl;

import ir.fathi.library.dao.BookDao;
import ir.fathi.library.dao.db.DBConfiguration;
import ir.fathi.library.dao.db.IDGenerator;
import ir.fathi.library.model.Book;
import ir.fathi.library.model.Genre;
import ir.fathi.library.model.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDaoImpl implements BookDao {
    private static BookDao bookDao=null;
    private final Connection connection = DBConfiguration.getDbConfigurationInstance().getConnectionInstance();
    private BookDaoImpl() {
    }
    public static BookDao getBookDaoInstance(){
        if (bookDao ==null){
            bookDao=new BookDaoImpl();
        }
        return bookDao;
    }

    @Override
    public Long save(Book book){
        var bookId = IDGenerator.nextId();
        try (var statement = connection.prepareStatement("insert into book(id,title,isbn,genre_id,publisher_id,author_id)values (?,?,?,?,?,?)")) {
            statement.setLong(1, bookId);
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getIsbn());
            statement.setLong(4, book.getGenre().getId());
            statement.setLong(5, book.getPublisher().getId());
            statement.setLong(6, book.getAuthors().get(0).getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return bookId;
    }

    @Override
    public List<Book> getAllBook() {
            try (var statement = connection.prepareStatement("select b.* from book b ")) {
                var result = statement.executeQuery();
                if (result.getFetchSize() > 0) {
                    List<Book> books = new ArrayList<>();
                    Book book;
                    while (result.next()) {
                        book = new Book(
                                result.getLong("id"),
                                result.getString("title"),
                                result.getString("isbn")
                        );
                        books.add(book);
                    }
                    return books;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return Collections.emptyList();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        try (var statement = connection.prepareStatement("select from book t where t.id = ?")) {
           statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            Genre genre = GenreDaoImpl.getGenreDaoInstance().getGenreById(result.getLong("genre_id")).get();
            Publisher publisher = PublisherDaoImpl.getPublisherDaoInstance().getPublisherById(result.getLong("publisher_id")).get();

            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Book book = new Book(
                    result.getLong("id"),
                    result.getString("title"),
                    result.getString("isbn"),
                    genre,
                    publisher
            );
            return Optional.of(book);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

}
