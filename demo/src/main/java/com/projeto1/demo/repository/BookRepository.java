package com.projeto1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto1.demo.Entity.Livro;

public interface BookRepository extends JpaRepository<Livro, Long> {

}
