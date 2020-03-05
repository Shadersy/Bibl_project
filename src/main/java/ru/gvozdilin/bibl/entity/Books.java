package ru.gvozdilin.bibl.entity;

public class Books {

    private int id;
    private String name;
    private String author;
    public Books() {

    }
    public Books(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAutor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }


}
