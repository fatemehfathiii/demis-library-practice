package ir.fathi.library.model;

import java.util.List;

public class Book {
    private long id;
    private String title;
    private String isbn;
    private Genre genre;
    private Publisher publisher;
    private List<Author> authors;

    public Book() {
    }

    public Book(long id, String title, String isbn, Genre genre, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Book(long id, String title, String isbn) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
