package com.teimour.wordsaver.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/11/2020
 */

@Component
public class DataBaseUserDetailsService implements UserDetailsService {

    private final DataSource dataSource;

    public DataBaseUserDetailsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = "";
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT user_id, password FROM users WHERE username=?");
            PreparedStatement authorityStatement =
                    connection.prepareStatement("SELECT authorities FROM user_authorities WHERE user_user_id=?")
        ) {
            statement.setObject(1, username);
            ResultSet result = statement.executeQuery();
            result.next();
            int userId = result.getInt("user_id");
            password = result.getString("password");

            authorityStatement.setObject(1, userId);
            ResultSet authoritiesResult = authorityStatement.executeQuery();

            while (authoritiesResult.next()) {
                authorities.add(new SimpleGrantedAuthority(authoritiesResult.getString("authorities")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User(username, password, authorities);
    }
}
