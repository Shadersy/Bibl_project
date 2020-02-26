package ru.gvozdilin.bibl.dao;



import ru.gvozdilin.bibl.entity.Books;

import java.util.List;

public interface BooksDao {

    List<Books> findAll();
    List<Books> sortByAuthor();
    List<Books> sortByName();

    public void deleteBooks(Integer id);
    public void addBooks(String name, String author);

}