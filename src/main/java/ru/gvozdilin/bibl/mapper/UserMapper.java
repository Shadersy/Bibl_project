package ru.gvozdilin.bibl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.bibl.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }
}
