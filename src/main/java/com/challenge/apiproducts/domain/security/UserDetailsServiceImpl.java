package com.challenge.apiproducts.domain.security;

import com.challenge.apiproducts.domain.users.UserModel;
import com.challenge.apiproducts.domain.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userService.findByUsername(username);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(getAuthority(userModel));
            return new User(userModel.getUsername(), userModel.getPassword(), authorities);
        }
    }

    private GrantedAuthority getAuthority(UserModel model) {
        if (model.getRole().equals("admin")) {
            return new SimpleGrantedAuthority("admin");
        }
        if (model.getRole().equals("user")) {
            return new SimpleGrantedAuthority("user");
        }
        if (model.getRole().equals("guest")) {
            return new SimpleGrantedAuthority("guest");
        }
        return null;
    }
}
