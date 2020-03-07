package ru.gvozdilin.bibl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.mapper.BooksMapper;

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
    public List<Books> sortByAuthor() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY author", new BooksMapper());
    }

    @Override
    public List<Books> sortByName() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY name", new BooksMapper());
    }

    @Override
    public void takeBooks(Integer booksId, Integer userId) {
        jdbcTemplate.update("UPDATE books SET userId = ? where id= ?", userId, booksId);
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
    public List<Books> getEmployeesByPage(int pageId, int total) {
        String sql="SELECT * FROM BOOKS LIMIT" +(pageId-1)+","+total;

        return jdbcTemplate.query(sql, new RowMapper<Books>() {
            @Override
            public Books mapRow(ResultSet resultSet, int i) throws SQLException {
                Books books = new Books();
                books.setId(resultSet.getInt(1));
                books.setName(resultSet.getString(2));
                books.setAutor(resultSet.getString(3));

            return books;
            }
        });
    }
}