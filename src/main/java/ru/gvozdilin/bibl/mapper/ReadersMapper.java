package ru.gvozdilin.bibl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.bibl.entity.Readers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadersMapper implements RowMapper<Readers> {

    @Override
    public Readers mapRow(ResultSet resultSet, int i) throws SQLException {
        Readers readers = new Readers();
        readers.setId(resultSet.getInt("id"));
        readers.setFirst_name(resultSet.getString("first_name"));
        readers.setSecond_name(resultSet.getString("second_name"));
        readers.setEmail(resultSet.getString("email"));
        readers.setPassword(resultSet.getString("password"));
    return  readers;
    }
}
