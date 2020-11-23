package com.teimour.wordsaver.security;

import com.teimour.wordsaver.security.user.User;
import com.teimour.wordsaver.security.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/11/2020
 */

@Component
public class DataBaseUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public DataBaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities=user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}
