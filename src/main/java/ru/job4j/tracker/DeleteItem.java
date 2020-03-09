package ru.job4j.tracker;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction {

    private Consumer<String> out;

    public DeleteItem(int key, String name, Consumer<String> out) {
        super(key, name);
        this.out = out;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("----------------Удаление заявки по id------------");
        String id = input.ask("Введите id заявки : ");
        if (tracker.delete(id)) {
            out.accept("Item was deleted");
        } else {
            out.accept("Item not found");
        }
    }
}
