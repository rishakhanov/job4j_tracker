package ru.job4j.tracker;

import java.util.function.Consumer;

public class FindItem extends BaseAction {

    private Consumer<String> out;

    public FindItem(int key, String name, Consumer<String> out) {
        super(key, name);
        this.out = out;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        out.accept("----------------Поиск заявки по id------------");
        String id = input.ask("Введите id заявки : ");
        Item result = tracker.findById(id);
        if (result != null) {
            out.accept("Заявка с id : " + result.getId() + " и с именем : " + result.getName());
        }
    }
}
