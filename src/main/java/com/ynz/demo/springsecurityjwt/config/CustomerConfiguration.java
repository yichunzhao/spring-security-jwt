package com.ynz.demo.springsecurityjwt.config;

import com.ynz.demo.springsecurityjwt.dto.PersonDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    PersonDto getPerson() {
        return new PersonDto("Dene", "Heinecke");
    }

    @Bean
    PersonDto getAnotherPerson() {
        return new PersonDto("Tabbitha", "Feldharker");
    }

    @Bean
    PersonDto getOneMorePerson() {
        return new PersonDto("Aland", "Meddemmen");
    }

}
