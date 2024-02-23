package ir.fathi.library.model.dto;

public class GetBookDetailResponse {
    private String title;
    private String isbn;
    private String author;
    private String genre;
    private String publisher;

    public GetBookDetailResponse(String title, String isbn, String author, String genre, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }
    public static GetBookDetailResponse toGetBookDetailResponse(String title, String isbn, String author, String genre, String publisher){
        return new GetBookDetailResponse(title,isbn,author,genre,publisher);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
