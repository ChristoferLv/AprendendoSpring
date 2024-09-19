package com.projeto1.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;
import com.projeto1.demo.dto.MessageResponseDTO;
import com.projeto1.demo.exception.BookNotFoundException;
import com.projeto1.demo.mapper.LivroMapper;
import com.projeto1.demo.repository.BookRepository;

@Service
public class LivroService {
    private BookRepository livroRepository;
    private final LivroMapper livroMapper = LivroMapper.INSTANCE;

    @Autowired
    public LivroService(BookRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    
     public MessageResponseDTO createLivro(LivroDTO livro) {
        /*Livro livroToSave = Livro.builder()
        .name(livro.getName())
        .pages(livro.getPages())
        .chapters(livro.getChapters())
        .autor(livro.getAutor())
        .build();*/
        Livro livroDTO = livroMapper.toEntity(livro);


        Livro savedLivro = livroRepository.save(livroDTO);
        return MessageResponseDTO.builder()
                .message("Livro criado com Id " + savedLivro.getId())
                .build();
    }

    public LivroDTO findById(Long id) throws BookNotFoundException {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
    
        return livroMapper.toDTO(livro);
    }
    
}
