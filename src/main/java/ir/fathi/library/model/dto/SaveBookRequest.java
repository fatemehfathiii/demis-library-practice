package ir.fathi.library.model.dto;

public class SaveBookRequest {
    private String title;
    private String isbn;
    private Long genre;
    private String authorFullName;
    private String publisher;

    public SaveBookRequest(String title, String isbn, Long genre, String authorFullName, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.authorFullName = authorFullName;
        this.publisher = publisher;
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

    public Long getGenre() {
        return genre;
    }

    public void setGenre(Long genre) {
        this.genre = genre;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
