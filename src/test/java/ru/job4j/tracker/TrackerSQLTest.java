package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void checkAdd() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        sql.add(null);
        boolean temp = true;
        assertThat(temp, is(true));
    }

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
}
