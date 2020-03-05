package ru.gvozdilin.bibl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.bibl.entity.Books;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksMapper implements RowMapper<Books> {

    @Override
    public Books mapRow(ResultSet resultSet, int i) throws SQLException {
        Books books = new Books();
        books.setId(resultSet.getInt("id"));
        books.setName(resultSet.getString("name"));
        books.setAutor(resultSet.getString("author"));

        return books;
    }
}
