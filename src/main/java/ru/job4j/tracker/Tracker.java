package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */

import java.util.*;


public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    private static final Random RN = new Random();

    /**
     * Метод реализующий добавление заявки в хранилище.
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public List<Item> findAll() {
        return items;
    }

    public boolean replace(String id, Item itemnew) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.set(i, itemnew);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item element : this.items) {
            if (element != null && element.getName().equals(key)) {
                result.add(element);
            }
        }
        return result;
    }
}
