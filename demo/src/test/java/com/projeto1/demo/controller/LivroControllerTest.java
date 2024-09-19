package com.projeto1.demo.controller;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.github.javafaker.Book;
import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;
import com.projeto1.demo.dto.MessageResponseDTO;
import com.projeto1.demo.service.LivroService;
import com.projeto1.demo.utils.LivroUtils;
import static com.projeto1.demo.utils.LivroUtils.createFakeLivroDTO;

import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {
    private MockMvc mockMvc;
    @Mock
    private LivroService livroService;
    @InjectMocks
    private LivroController livroController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(livroController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTIsCalledThenALivroShouldBeCreated() {
        LivroDTO bookDTO = LivroUtils.createFakeLivroDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Livro criado com Id " + bookDTO.getId())
                .build();

        Mockito.when(livroService.createLivro(bookDTO)).thenReturn(expectedMessageResponse);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/livros")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(LivroUtils.asJsonString(bookDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() {
        LivroDTO bookDTO = createFakeLivroDTO();
        bookDTO.setIsbn("invalid isbn");

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/livros")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(LivroUtils.asJsonString(bookDTO)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
