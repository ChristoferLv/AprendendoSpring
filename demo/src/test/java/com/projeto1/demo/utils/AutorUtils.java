package com.projeto1.demo.utils;

import com.github.javafaker.Faker;
import com.projeto1.demo.Entity.Autor;
import com.projeto1.demo.dto.AutorDTO;

public class AutorUtils {

    private static final Faker faker = Faker.instance();

    public static AutorDTO createFakeAutorDTO() {
        return AutorDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }

    public static Autor createFakeAuthor() {
        return Autor.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }

    public static Autor createFakeAuthorFrom(AutorDTO autorDTO) {
        return Autor.builder()
                .id(autorDTO.getId())
                .name(autorDTO.getName())
                .age(autorDTO.getAge())
                .build();
    }
}