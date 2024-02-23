package ir.fathi.library.dao.impl;

import ir.fathi.library.dao.PublisherDao;
import ir.fathi.library.dao.db.DBConfiguration;
import ir.fathi.library.dao.db.IDGenerator;
import ir.fathi.library.model.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PublisherDaoImpl implements PublisherDao {
    private static PublisherDao publisherDao = null;
    private final Connection connection = DBConfiguration.getDbConfigurationInstance().getConnectionInstance();

    private PublisherDaoImpl() {
    }

    public static PublisherDao getPublisherDaoInstance() {
        if (publisherDao == null) {
            publisherDao = new PublisherDaoImpl();
        }
        return publisherDao;
    }

    @Override
    public Long savePublisher(String title) {
        var publisherId = IDGenerator.nextId();
        try (var statement = connection.prepareStatement("insert into publisher(id,title)values(?,?)")) {
            statement.setLong(1, publisherId);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return publisherId;
    }

    @Override
    public Optional<Publisher> getPublisherByTitle(String title) {
        try (var statement = connection.prepareStatement("select from publisher t where t.title = ?")) {
            statement.setString(1, title);
            ResultSet result = statement.executeQuery();
            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Publisher publisher = new Publisher(
                    result.getLong("id"),
                    result.getString("title")
            );
            return Optional.of(publisher);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Publisher> getPublisherById(Long id) {
        try (var statement = connection.prepareStatement("select from publisher t where t.id = ?")) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.getFetchSize() == 0) {
                return Optional.empty();
            }
            Publisher publisher = new Publisher(
                    result.getLong("id"),
                    result.getString("title")
            );
            return Optional.of(publisher);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
