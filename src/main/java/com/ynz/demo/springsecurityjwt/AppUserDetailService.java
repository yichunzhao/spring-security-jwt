package com.ynz.demo.springsecurityjwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserDetailService implements UserDetailsService {
    private Map<String, UserDetails> stringUserDetailsMap = new HashMap<>();

    {
        stringUserDetailsMap.put("foo", User.builder().username("foo").password("foo").authorities("USER").build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return stringUserDetailsMap.get(username);
    }

}
