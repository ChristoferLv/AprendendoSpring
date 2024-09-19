package com.projeto1.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;

import static com.projeto1.demo.utils.AutorUtils.createFakeAuthor;
import static com.projeto1.demo.utils.AutorUtils.createFakeAutorDTO;
import static com.projeto1.demo.utils.AutorUtils.createFakeAuthorFrom;

public class LivroUtils {

    private static final Faker faker = Faker.instance();

    public static LivroDTO createFakeLivroDTO() {
        return LivroDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher())
                .autor(createFakeAutorDTO())
                .build();
    }

    public static Livro createFakeLivro() {
        return Livro.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher())
                .autor(createFakeAuthor())
                .build();
    }

    public static Livro createFakeLivroFrom(LivroDTO LivroDTO) {
        return Livro.builder()
                .id(LivroDTO.getId())
                .name(LivroDTO.getName())
                .pages(LivroDTO.getPages())
                .chapters(LivroDTO.getChapters())
                .isbn(LivroDTO.getIsbn())
                .publisherName(LivroDTO.getPublisherName())
                .autor(createFakeAuthorFrom(LivroDTO.getAutor()))
                .build();
    }

    public static String asJsonString(LivroDTO LivroDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(LivroDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
