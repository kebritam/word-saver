package com.teimour.wordsaver.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.*;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/11/2020
 */

public class DataBaseUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password="";
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/word_saver",
                    "postgres", "48625");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username='"+username+"';");
            resultSet.next();
            password=resultSet.getObject("password", String.class);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
