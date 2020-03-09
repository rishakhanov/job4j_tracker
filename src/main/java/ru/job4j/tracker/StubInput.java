package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
    private final String[] value;
    private int position;


    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String input, List<Integer> range) {
        int key = Integer.valueOf(this.ask(input));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}
