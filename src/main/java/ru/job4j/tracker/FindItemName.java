package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FindItemName extends BaseAction {

    private Consumer<String> out;

    public FindItemName(int key, String name, Consumer<String> out) {
        super(key, name);
        this.out = out;
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        System.out.println("----------------Поиск заявки по имени------------");
        String name = input.ask("Введите имя заявки : ");
        List<Item> result = tracker.findByName(name);
        if (result.size() > 0) {
            for (Item item : result) {
                out.accept("Заявка с id : " + item.getId() + " и с именем : " + item.getName());
            }
        }
    }
}
