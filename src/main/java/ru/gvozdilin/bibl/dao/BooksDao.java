package ru.gvozdilin.bibl.dao;


import ru.gvozdilin.bibl.entity.Books;

import java.util.List;

public interface BooksDao {

    List<Books> findAll();

    List<Books> sortByAuthor();

    List<Books> sortByName();

    void takeBooks(Integer booksId, Integer userId);

    void deleteBooks(Integer id);

    void editBooks(String name, Integer id);

    void addBooks(String name, String author);

    public List<Books> getEmployeesByPage(int pageId, int total);

}