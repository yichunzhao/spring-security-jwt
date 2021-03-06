package com.ynz.demo.springsecurityjwt;

import com.ynz.demo.springsecurityjwt.dto.PersonDto;
import com.ynz.demo.springsecurityjwt.models.AuthenticationRequest;
import com.ynz.demo.springsecurityjwt.models.AuthenticationResponse;
import com.ynz.demo.springsecurityjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
@RequiredArgsConstructor
public class SpringSecurityJwtApplication {
    private final JwtUtil jwtUtil;
    private final List<PersonDto> personList;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @GetMapping("persons")
    public List<PersonDto> getPersonList() {
        log.info("Get persons ...");
        return personList;
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
        log.info("Create jwt token...");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        log.info("jwt: {}", jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
