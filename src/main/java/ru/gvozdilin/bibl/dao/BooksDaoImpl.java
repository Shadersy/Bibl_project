package ru.gvozdilin.bibl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.entity.User;
import ru.gvozdilin.bibl.mapper.BooksMapper;
import ru.gvozdilin.bibl.mapper.JoinMapper;
import ru.gvozdilin.bibl.mapper.UserMapper;

import javax.persistence.criteria.Join;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BooksDaoImpl implements BooksDao {


    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Books> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", new BooksMapper());
    }

    @Override
    public List<Books> takenBooks(Long id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE userId = ?", new Object[]{id}, new BooksMapper());
    }


    @Override
    public List<Books> sortByAuthor() {
        return jdbcTemplate.query("SELECT books.id as id, books.name as name, books.author as author, books.userId as userId, user.username as username FROM books LEFT JOIN user ON books.userId = user.id ORDER BY books.author", new JoinMapper());
    }

    @Override
    public List<Books> sortByName() {
        return jdbcTemplate.query("SELECT books.id as id, books.name as name, books.author as author, books.userId as userId, user.username as username FROM books LEFT JOIN user ON books.userId = user.id ORDER BY books.name", new JoinMapper());
    }

    @Override
    public List<Books> showNotReservedBooks() {
        return jdbcTemplate.query("SELECT books.id as id, books.name as name, books.author as author, books.userId as userId, user.username as username FROM books LEFT JOIN user ON books.userId = user.id WHERE userId IS NULL", new JoinMapper());
    }


    public void takeBooks(Integer booksId, Long userId) {
        jdbcTemplate.update("UPDATE books SET userId = ? where id= ?", userId, booksId);
    }


    public List<Books> leftJoinBooksUser(){
        return  jdbcTemplate.query("SELECT books.id as id, books.name as name, books.author as author, books.userId as userId, user.username as username FROM books LEFT JOIN user ON books.userId = user.id", new JoinMapper());
    }

    @Override
    public List<Books> getBooksSortedPaginated(String sort, int pageSize, int pageCount) {
        String query = "SELECT books.id as id, books.name as name, books.author as author, books.userId as userId, user.username as username FROM books LEFT JOIN user ON books.userId = user.id";

        if (sort == null) {
            query += " ORDER BY id";
        } else if (sort.equals("sortByName")) {
            query += " ORDER BY name";
        } else if (sort.equals("sortByAuthor")) {
            query += " ORDER BY author";
        } else {
            query += " ORDER BY id";
        }

        if (pageCount < 0) {
            pageCount = 0;
        }

        if (pageSize < 1) {
            pageSize = 1;
        }

        query += " LIMIT " + pageSize + " OFFSET " + pageCount * pageSize;

        return jdbcTemplate.query(query, new JoinMapper());
    }


    public void deleteBooks(Integer id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public void editBooks(String name, Integer id) {
        jdbcTemplate.update("UPDATE books SET name = ? WHERE id = ?", name, id);
    }

    @Override
    public void addBooks(String name, String author) {
        jdbcTemplate.update("INSERT INTO books (name, author) VALUES (?, ?)", name, author);
    }

    @Override
    public void returnBooks(Long id) {
        jdbcTemplate.update("UPDATE books SET userId = null WHERE id = ? ", id);
    }




}
