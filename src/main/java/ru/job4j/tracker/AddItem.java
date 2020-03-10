package ru.job4j.tracker;

import java.util.function.Consumer;

public class AddItem extends BaseAction {

    private Consumer<String> out;

    public AddItem(int key, String name, Consumer<String> out) {
        super(key, name);
        this.out = out;
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        out.accept("---------------Добавление новой заявки----------------");
        String name = input.ask("Введите имя заявки : ");
        String desc = input.ask("Введите описание заявки : ");
        Item item = new Item(name, desc);
        tracker.add(item);
        out.accept("-----------Новая заявка с getId : " + item.getId() + "-----------");
    }
}
