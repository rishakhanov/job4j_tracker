package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;
import java.util.*;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    private static final Random RNN = new Random();

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try {
            PreparedStatement st = connection.prepareStatement("insert into items (id, name, description, "
                    + "create_, comments) values(?, ?, ?, ?, ?)");
            st.setString(1, this.generateId());
            st.setString(2, "Name6");
            st.setString(3, "Description6");
            st.setLong(4, 666);
            st.setString(5, "Comments6");
            int rowsAdded = st.executeUpdate();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RNN.nextInt());
    }

    @Override
    public boolean replace(String idNew, Item item) {
        boolean result = false;
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE items SET name=?, "
                    + "description=?, create_=?, comments=? WHERE id = ?");
            st.setString(1, "NameUpdated");
            st.setString(2, "DescriptionUpdated");
            st.setLong(3, 777);
            st.setString(4, "CommentsUpdated");
            st.setString(5, idNew);
            int rowsReplaced = st.executeUpdate();
            result = true;
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(String idNew) {
        boolean result = false;
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM items WHERE id = ?");
            st.setString(1, idNew);
            int rowsDeleted = st.executeUpdate();
            result = true;
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM items");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    @Override
    public List<Item> findByName(String nameKey) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE name = ?");
            st.setString(1, nameKey);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    @Override
    public Item findById(String idKey) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?");
            st.setString(1, idKey);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
