package ru.gvozdilin.bibl.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.mapper.BooksMapper;

import java.util.List;

public interface BooksDao {

    List<Books> findAll();
    List<Books> takenBooks(Long id);
    List<Books> sortByAuthor();
    List<Books> sortByName();
    List<Books> showNotReservedBooks();
    public List<Books> leftJoinBooksUser();
    public List<Books> getBooksSortedPaginated(String sort, int pageSize, int pageCount);



        void takeBooks(Integer booksId, Long userId);

    void deleteBooks(Integer id);

    void editBooks(String name, Integer id);

    void addBooks(String name, String author);


    void returnBooks(Long id);



}