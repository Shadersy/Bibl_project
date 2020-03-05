package ru.gvozdilin.bibl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gvozdilin.bibl.dao.BooksDao;
import ru.gvozdilin.bibl.entity.Books;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    public BooksDao booksDao;


    //'ru.gvozdilin.bibl.service.BooksService' available: expected single matching bean but found 2: booksServiceImpl,getBooksService
    @Override
    public List<Books> findAll() {
        return booksDao.findAll();
    }

    @Override
    public void editBooks(String name, Integer id) {
         booksDao.editBooks(name, id);
    }

    @Override
    public List<Books> sortByAuthor() {
        return booksDao.sortByAuthor();
    }

    @Override
    public List<Books> sortByName() {
        return booksDao.sortByName();
    }

    @Override
    public void deleteBooks(Integer id) {
        booksDao.deleteBooks(id);
    }

    @Override
    public void addBooks(String name, String author) {
        booksDao.addBooks(name, author);
    }


}
