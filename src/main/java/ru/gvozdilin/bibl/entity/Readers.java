package ru.gvozdilin.bibl.entity;

public class Readers {
    private int id;
    private String first_name;
    private String second_name;
    private String email;
    private String password;

    public void setId(int id){
        this.id=id;
    };

    public void setFirst_name(String first_name){
        this.first_name=first_name;
    };

    public void setSecond_name(String second_name){
        this.second_name=second_name;
    };

    public void setEmail(String email){
        this.email=email;
    };

    public void setPassword(String password){
        this.password=password;
    };

    public int getId(){return id;}
    public String getFirst_name(){return first_name; }
    public String getSecond_name(){return second_name;}
    public String getEmail(){return email;}
    public String getPassword(){return  password;}



}



