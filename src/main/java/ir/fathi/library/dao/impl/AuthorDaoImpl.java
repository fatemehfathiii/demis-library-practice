package ir.fathi.library.dao.impl;

import ir.fathi.library.dao.AuthorDao;
import ir.fathi.library.dao.db.DBConfiguration;
import ir.fathi.library.dao.db.IDGenerator;
import ir.fathi.library.model.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {
    private static AuthorDao authorDao = null;
    private final Connection connection = DBConfiguration.getDbConfigurationInstance().getConnectionInstance();

    private AuthorDaoImpl() {
    }

    public static AuthorDao getAuthorDaoInstance() {
        if (authorDao == null) {
            authorDao = new AuthorDaoImpl();
        }
        return authorDao;
    }


    @Override
    public Long saveAuthorDao(String fullName) {
        var authorId = IDGenerator.nextId();
        try (var statement = connection.prepareStatement("insert into author(id,fullName)values(?,?)")) {
            statement.setLong(1, authorId);
            statement.setString(2, fullName);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return authorId;
    }

    @Override
    public Optional<Author> getAuthorByFullName(String fullName) {
        try (var statement = connection.prepareStatement("from author t where t.fullName = ?")) {
            statement.setString(1, fullName);
            ResultSet result = statement.executeQuery();
            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Author author = new Author(
                    result.getLong("id"),
                    result.getString("fullName")
            );
            return Optional.of(author);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> getAuthorById(Long bookId) {
        try (var statement = connection.prepareStatement("select from author t where t.book_id = ?")) {
            statement.setLong(1, bookId);
            ResultSet result = statement.executeQuery();
            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Author author = new Author(
                    result.getLong("id"),
                    result.getString("fullName")
            );
            return Optional.of(author);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
