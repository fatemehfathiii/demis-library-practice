package ir.fathi.library.dao;

import ir.fathi.library.model.Publisher;

import java.util.Optional;

public interface PublisherDao {
    Long savePublisher(String title);
    Optional<Publisher> getPublisherByTitle(String title);
    Optional<Publisher> getPublisherById(Long id);
}
