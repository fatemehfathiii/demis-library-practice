package ir.fathi.library.model.dto;

import ir.fathi.library.model.Author;
import ir.fathi.library.model.Book;

public class GetBookResponse {
    private String title;
    private String isbn;


    public GetBookResponse() {
    }

    public GetBookResponse(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public static GetBookResponse toGetBookResponse(Book book){
        return new GetBookResponse(book.getTitle(), book.getIsbn());
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

}
