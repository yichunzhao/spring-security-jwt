package com.ynz.demo.springsecurityjwt;

import com.ynz.demo.springsecurityjwt.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringSecurityJwtApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringSecurityJwtApplication application;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void personListIsCooked() {
        List<PersonDto> cookedPersons = application.getPersonList();

        assertAll(
                () -> assertNotNull(cookedPersons),
                () -> assertThat(cookedPersons, hasSize(3))
        );
    }

}
