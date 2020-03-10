package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final ITracker tracker;
    private final Consumer<String> output;
    //private final ITracker tracker;

    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions();
        List<Integer> menufill = new ArrayList<>();
        menufill = menu.fillRanges();
        do {
            menu.show();
            menu.select(input.ask("select:", menufill));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

     public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}