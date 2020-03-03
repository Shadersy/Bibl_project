package ru.gvozdilin.bibl.service;

import ru.gvozdilin.bibl.entity.Books;

import java.util.List;



public interface BooksService {
    List<Books> findAll();
    public void deleteBooks(Integer id);
    public void addBooks(String name, String author);
    public List<Books> sortByAuthor();
    public List<Books> sortByName();


}
