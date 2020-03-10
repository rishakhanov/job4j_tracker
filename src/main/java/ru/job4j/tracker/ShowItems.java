package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class ShowItems extends BaseAction {

    private Consumer<String> out;

    public ShowItems(int key, String name, Consumer<String> out) {
        super(4, "Вывести все заявки");
        this.out = out;
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        out.accept("----------------Вывод всех заявок------------");
        List<Item> arrays = tracker.findAll();
        for (Item item : arrays) {
            out.accept(item.getName() + " " + item.getDescription());
        }
    }
}
