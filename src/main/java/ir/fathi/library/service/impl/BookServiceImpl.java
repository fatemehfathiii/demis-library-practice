package ir.fathi.library.service.impl;

import ir.fathi.library.exception.RecordNotFoundException;
import ir.fathi.library.model.dto.GetBookDetailResponse;
import ir.fathi.library.dao.impl.AuthorDaoImpl;
import ir.fathi.library.dao.impl.BookDaoImpl;
import ir.fathi.library.dao.impl.GenreDaoImpl;
import ir.fathi.library.dao.impl.PublisherDaoImpl;
import ir.fathi.library.model.Author;
import ir.fathi.library.model.Book;
import ir.fathi.library.model.Genre;
import ir.fathi.library.model.Publisher;
import ir.fathi.library.model.dto.GetBookResponse;
import ir.fathi.library.model.dto.SaveBookRequest;
import ir.fathi.library.service.BookService;

import java.util.List;


public class BookServiceImpl implements BookService {
    @Override
    public void save(SaveBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        setGenre(request, book);
        setAuthor(request, book);
        setPublisher(request, book);
        BookDaoImpl.getBookDaoInstance().save(book);
    }

    @Override
    public List<GetBookResponse> getBooks() {
        return BookDaoImpl.getBookDaoInstance().getAllBook().stream().map(GetBookResponse::toGetBookResponse).toList();
    }

    @Override
    public GetBookDetailResponse getDetail(Long bookId){
        Book book = BookDaoImpl.getBookDaoInstance().getBookById(bookId).orElseThrow(RecordNotFoundException::new);
        Author author = AuthorDaoImpl.getAuthorDaoInstance().getAuthorById(book.getId()).orElseThrow(RecordNotFoundException::new);
        return new GetBookDetailResponse(book.getTitle(), book.getIsbn(), author.getFullName(),book.getGenre().getTitle(),book.getPublisher().getTitle());
    }

    private void setGenre(SaveBookRequest request, Book book) {
        book.setGenre(GenreDaoImpl.getGenreDaoInstance().getGenreById(request.getGenre()).orElse(new Genre(-1L, "unKnown")));
    }

    private void setAuthor(SaveBookRequest request, Book book) {
        var authorDao = AuthorDaoImpl.getAuthorDaoInstance();
        if (request.getAuthorFullName() != null) {
            var authorByFullName = authorDao.getAuthorByFullName(request.getAuthorFullName());
            if (authorByFullName.isPresent()) {
                book.setAuthors(List.of(authorByFullName.get()));
            } else {
                var createdAuthor = authorDao.getAuthorById(authorDao.saveAuthorDao(request.getAuthorFullName()));
                createdAuthor.ifPresent(author -> book.setAuthors(List.of(author)));
            }
        } else {
            book.setAuthors(List.of(new Author(-1L, "unKnown")));
        }
    }

    private void setPublisher(SaveBookRequest request, Book book) {
        var publisherDao = PublisherDaoImpl.getPublisherDaoInstance();
        if (request.getPublisher() != null) {
            var publisherByTitle = publisherDao.getPublisherByTitle(request.getTitle());
            if (publisherByTitle.isPresent()) {
                book.setPublisher(publisherByTitle.get());
            } else {
                var createdPublisher = publisherDao.getPublisherById(publisherDao.savePublisher(request.getTitle()));
                createdPublisher.ifPresent(publisher -> book.setPublisher(createdPublisher.get()));
            }
        } else {
            book.setPublisher(new Publisher(-1L, "unKnown"));
        }
    }
}
