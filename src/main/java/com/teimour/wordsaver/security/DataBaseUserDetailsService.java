package com.teimour.wordsaver.security;

import org.springframework.data.util.Pair;
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

        try(Connection connection = dataSource.getConnection()) {
            Pair<Integer, String> idAndPassword= getIdAndPassword(username, connection);
            int userId = idAndPassword.getFirst();
            password = idAndPassword.getSecond();

            authorities = getAuthorities(userId, connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User(username, password, authorities);
    }

    private Pair<Integer, String> getIdAndPassword(String username, Connection connection) {
        int id = -1;
        String password = "";
        try(PreparedStatement statement =
                    connection.prepareStatement("SELECT user_id, password FROM users WHERE username=?")
        ) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            result.next();
            id = result.getInt("user_id");
            password = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (id != -1) {
            return Pair.of(id, password);
        } else {
            throw new UsernameNotFoundException("username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(int id, Connection connection) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        try(PreparedStatement statement =
                    connection.prepareStatement("SELECT authorities FROM user_authorities WHERE user_user_id=?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                authorities.add(new SimpleGrantedAuthority(resultSet.getString("authorities")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorities;
    }

}
