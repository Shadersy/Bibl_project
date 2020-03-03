package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.entity.User;

import java.util.List;

public interface UserDao {



    public List<User> getByLogin();
}
