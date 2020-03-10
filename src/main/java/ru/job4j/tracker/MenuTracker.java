package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private final Consumer<String> output;


    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public int getActionsLength() {
        return this.actions.size();
    }

    public void fillActions() {
        this.actions.add(new AddItem(0, "Добавление заявки", output));
        this.actions.add(new UpdateItem(1, "Обновление заявки", output));
        this.actions.add(new FindItem(2, "Поиск заявки", output));
        this.actions.add(new DeleteItem(3, "Удаление заявки", output));
        this.actions.add(new ShowItems(4, "Вывести все заявки", output));
        this.actions.add(new FindItemName(5, "Поиск заявки по имени", output));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    public List<Integer> fillRanges() {
        List<Integer> ranges = new ArrayList<>();
        for (int i = 0; i < getActionsLength(); i++) {
            ranges.add(i);
        }
        return ranges;
    }

}
