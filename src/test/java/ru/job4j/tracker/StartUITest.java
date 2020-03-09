package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static java.lang.System.out;
import static org.hamcrest.core.Is.is;
//import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private Tracker tracker;
    private final Consumer<String> output = new Consumer<String>() {
        private final ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(out);
        @Override
        public void accept(String s) {
            stream.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };
    private final PrintStream stdout = System.out;

    public static final String MENU = "0 : Добавление заявки"
                                        + System.lineSeparator() +  "1 : Обновление заявки" + System.lineSeparator()
                                        + "2 : Поиск заявки" +  System.lineSeparator() + "3 : Удаление заявки"
                                        + System.lineSeparator() + "4 : Вывести все заявки" + System.lineSeparator()
                                        + "5 : Поиск заявки по имени" + System.lineSeparator();

    @Before
    public void loadOutput() {
        tracker = new Tracker();
        tracker.add(new Item("name1", "desc1"));
        tracker.add(new Item("name2", "desc2"));
        tracker.add(new Item("name3", "desc3"));
        tracker.add(new Item("name4", "desc4"));
        tracker.add(new Item("name5", "desc5"));
        tracker.add(new Item("name6", "desc6"));
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserSelectGetAllThenTrackerReturnsAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"4", "y"});
        new StartUI(input, tracker, this.output).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(MENU)
                                .append("----------------Вывод всех заявок------------")
                                .append(System.lineSeparator())
                                .append("name1" + " " + "desc1")
                                .append(System.lineSeparator())
                                .append("name2" + " " + "desc2")
                                .append(System.lineSeparator())
                                .append("name3" + " " + "desc3")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindItemThenTrackerReturnFoundValue2() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"2", item2.getId(), "y"});
        new StartUI(input, tracker, output).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(MENU)
                                .append("----------------Поиск заявки по id------------")
                                .append(System.lineSeparator())
                                .append("Заявка с id : " + item2.getId() + " и с именем : " + item2.getName())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName2() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker, output).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append(MENU)
                                .append("---------------Добавление новой заявки----------------")
                                .append(System.lineSeparator())
                                .append("-----------Новая заявка с getId : " + tracker.findAll().get(0).getId() + "-----------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"1", item.getId(), "test replace", "заменили заявку", "y"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteThenTrackerDoesNotHaveDeletedValue() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"3", item2.getId(), "y"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(1).getName(), is("name3"));
    }

    @Test
    public void whenFindItemThenTrackerReturnFoundValue() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"2", item2.getId(), "y"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findById(item2.getId()).getName(), is("name2"));
    }
}
