package ru.job4j.tracker;

public class Item {
    private String id;

    public String name;

    public String description;

    public long create;

    public String comments;

    public Item() {
    }

    public Item(String name, String description, long create, String comments) {
        this.name = name;
        this.description = description;
        this.create = create;
        this.comments = comments;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getCreate() {
        return this.create;
    }

    public String getComments() {
        return comments;
    }

    public void setId(String id) {
        this.id = id;
    }
}
