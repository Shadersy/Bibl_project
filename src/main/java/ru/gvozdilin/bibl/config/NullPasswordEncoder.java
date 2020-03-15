package ru.gvozdilin.bibl.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NullPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword.toString());
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
