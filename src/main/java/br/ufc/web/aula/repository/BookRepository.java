package br.ufc.web.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.aula.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(String isbn);
}