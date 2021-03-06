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

    @Override
    public List<Books> findAll() {
        return booksDao.findAll();
    }

    @Override
    public void editBooks(String name, Integer id) {
         booksDao.editBooks(name, id);
    }

    @Override
    public void takeBooks(Integer booksId, Long userId) {
        booksDao.takeBooks(booksId, userId);
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
    public List<Books> takenBooks(Long id) {
        return booksDao.takenBooks(id);
    }

    @Override
    public List<Books> showNotReservedBooks() {
        return booksDao.showNotReservedBooks();
    }

    @Override
    public List<Books> leftJoinBooksUser() {
        return booksDao.leftJoinBooksUser();
    }


    @Override
    public void returnBooks(Long id) {
        booksDao.returnBooks(id);
    }

    @Override
    public List<Books> getBooksSortedPaginated(String sort, int pageSize, int pageCount) {
        return booksDao.getBooksSortedPaginated(sort, pageSize, pageCount);
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
