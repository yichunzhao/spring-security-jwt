package com.ynz.demo.springsecurityjwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserDetailService implements UserDetailsService {
    private final Map<String, UserDetails> stringUserDetailsMap = new HashMap<>();

    {
        stringUserDetailsMap.put("foo", User.builder().username("foo").password("foo").authorities("ROLE_USER").build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (stringUserDetailsMap.get(username) == null)
            throw new UsernameNotFoundException(username + " is not found!");
        return stringUserDetailsMap.get(username);
    }

}
