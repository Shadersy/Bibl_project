package ru.gvozdilin.bibl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gvozdilin.bibl.dao.UserDaoImpl;
import ru.gvozdilin.bibl.entity.Roles;
import ru.gvozdilin.bibl.entity.User;

import java.util.HashSet;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoImpl userdao;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userdao.getByLogin(username);

        HashSet<GrantedAuthority> roles = new HashSet();

        roles.add(new SimpleGrantedAuthority(Roles.USER.name()));

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
