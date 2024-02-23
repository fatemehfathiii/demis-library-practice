package ir.fathi.library.service;

import ir.fathi.library.exception.RecordNotFoundException;
import ir.fathi.library.model.dto.GetBookDetailResponse;
import ir.fathi.library.model.dto.GetBookResponse;
import ir.fathi.library.model.dto.SaveBookRequest;

import java.util.List;

public interface BookService {
    void save(SaveBookRequest request);

    List<GetBookResponse> getBooks();

    GetBookDetailResponse getDetail(Long bookId) throws RecordNotFoundException;
}
