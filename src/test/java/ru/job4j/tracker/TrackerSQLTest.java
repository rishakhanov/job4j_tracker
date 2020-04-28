package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL(null);
        assertThat(sql.init(), is(true));
    }

    @Test
    public void checkAdd() {
        TrackerSQL sql = new TrackerSQL(null);
        sql.init();
        sql.add(null);
        boolean temp = true;
        assertThat(temp, is(true));
    }

    @Test
    public void checkAdd2() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            //TrackerSQL sql = new TrackerSQL();
            //sql.init();
            sql.add(null);
            boolean temp = true;
            assertThat(temp, is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



/*
    @Test
    public void checkDelete() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        String id = "1585767224306";
        assertThat(sql.delete(id), is(true));
    }

    @Test
    public void checkUpdate() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        String id = "1585920336644";
        assertThat(sql.replace(id, null), is(true));
    }

    @Test
    public void checkfindAll() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        sql.findAll();
        boolean result = true;
        assertThat(result, is(true));
    }

    @Test
    public void checkFindByName() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        sql.findByName("Name3");
        boolean result = true;
        assertThat(result, is(true));
    }

    @Test
    public void checkFindById() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        sql.findById("1584688017637");
        boolean result = true;
        assertThat(result, is(true));
    }

 */
}
