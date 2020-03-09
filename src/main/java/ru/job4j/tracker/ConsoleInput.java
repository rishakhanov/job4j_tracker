package ru.job4j.tracker;

import java.util.*;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String input) {
        System.out.println(input);
        return scanner.nextLine();
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
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }

    }
}
