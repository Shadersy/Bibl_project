package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.entity.User;



public interface UserDao {



    public User getByLogin(String username);
}
