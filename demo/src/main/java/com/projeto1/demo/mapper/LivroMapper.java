package com.projeto1.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projeto1.demo.Entity.Livro;
import com.projeto1.demo.dto.LivroDTO;

@Mapper
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    Livro toEntity(LivroDTO livroDTO);

    LivroDTO toDTO(Livro livro);
}