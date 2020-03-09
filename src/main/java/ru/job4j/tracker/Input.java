package ru.job4j.tracker;

import java.util.List;

public interface Input {
    String ask(String input);

    int ask(String input, List<Integer> range);
}
