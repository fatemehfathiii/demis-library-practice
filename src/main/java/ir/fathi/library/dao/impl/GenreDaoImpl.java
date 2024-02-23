package ir.fathi.library.dao.impl;

import ir.fathi.library.dao.GenreDao;
import ir.fathi.library.dao.db.DBConfiguration;
import ir.fathi.library.dao.db.IDGenerator;
import ir.fathi.library.model.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {
    private static GenreDao genreDao;
    private final Connection connection = DBConfiguration.getDbConfigurationInstance().getConnectionInstance();

    private GenreDaoImpl() {
    }

    public static GenreDao getGenreDaoInstance() {
        if (genreDao == null) {
            genreDao = new GenreDaoImpl();
        }
        return genreDao;
    }

    @Override
    public Long saveGenreDao(String title) {
        var genreId = IDGenerator.nextId();
        try (var statement = connection.prepareStatement("insert into publisher(id,title)values(?,?)")) {
            statement.setLong(1, genreId);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return genreId;
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        try (var statement = connection.prepareStatement("select from genre t where t.id = ?")) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Genre genre = new Genre(
                    result.getLong("id"),
                    result.getString("title")
            );
            return Optional.of(genre);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
