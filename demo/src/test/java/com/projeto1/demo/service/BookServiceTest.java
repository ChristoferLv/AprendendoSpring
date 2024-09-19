package com.projeto1.demo.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Book;
import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;
import com.projeto1.demo.exception.BookNotFoundException;
import com.projeto1.demo.repository.BookRepository;
import com.projeto1.demo.utils.LivroUtils;
import com.projeto1.demo.utils.LivroUtils.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LivroService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Livro expectedFoundLivro = LivroUtils.createFakeLivro();

        Mockito.when(bookRepository.findById(expectedFoundLivro.getId())).thenReturn(Optional.of(expectedFoundLivro));
    
        LivroDTO livroDTO = bookService.findById(expectedFoundLivro.getId());

        Assertions.assertEquals(expectedFoundLivro.getName(), livroDTO.getName());
        Assertions.assertEquals(expectedFoundLivro.getIsbn(), livroDTO.getIsbn());
        Assertions.assertEquals(expectedFoundLivro.getPublisherName(), livroDTO.getPublisherName());
    }

    @Test
    void whenGivenUnexistingIdThenReturnThisBook() {
        var invalidId = 10L;

        Mockito.when(bookRepository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Livro.class)));

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }
}
