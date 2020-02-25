package ru.gvozdilin.bibl.dao;

import ru.gvozdilin.bibl.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
}
