package ru.job4j.tracker;

import java.util.function.Consumer;

public class UpdateItem extends BaseAction {

    private Consumer<String> out;

    public UpdateItem(int key, String name, Consumer<String> out) {
        super(key, name);
        this.out = out;
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        System.out.println("-----------------Обновление заявки------------");
        String id = input.ask("Введите id заявки : ");
        String name = input.ask("Введите имя заявки : ");
        String desc = input.ask("Введите описание заявки : ");
        Item item = new Item(name, desc);
        if (tracker.replace(id, item)) {
            out.accept("Item was updated");
        } else {
            out.accept("Item not found");
        }
    }
}
