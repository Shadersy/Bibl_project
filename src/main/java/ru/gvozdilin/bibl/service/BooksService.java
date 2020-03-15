package ru.gvozdilin.bibl.service;

import ru.gvozdilin.bibl.entity.Books;

import java.util.List;



public interface BooksService {
    List<Books> findAll();

    void editBooks(String name, Integer id);
    void takeBooks(Integer booksId, Long userId);

    void deleteBooks(Integer id);

    void addBooks(String name, String author);

    List<Books> sortByAuthor();

    List<Books> sortByName();
    List<Books> takenBooks(Long id);
    List<Books> showNotReservedBooks();
    public List<Books> leftJoinBooksUser();

    public void returnBooks(Long id);

    public List<Books> getBooksSortedPaginated(String sort, int pageSize, int pageCount);
}
