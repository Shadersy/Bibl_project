package ru.gvozdilin.bibl.entity;

public class Books {

    public Books(){

    }


    public Books(int id, String name, String author){
        this.id=id;
        this.name = name;
        this.author = author;
    }


    private int id;
    private String name;
    private String author;




    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAutor(String author){
        this.author=author;
    }

    public String getAuthor(){
        return author;
    }


}
