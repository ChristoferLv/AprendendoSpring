package com.projeto1.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;
import com.projeto1.demo.dto.MessageResponseDTO;
import com.projeto1.demo.exception.BookNotFoundException;
import com.projeto1.demo.service.LivroService;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public MessageResponseDTO createLivro(@RequestBody @Valid LivroDTO livroDTO) {
        return livroService.createLivro(livroDTO);
    }

    @GetMapping("/{id}")
    public LivroDTO findById(@PathVariable Long id) throws BookNotFoundException {
        return livroService.findById(id);
    }
}
