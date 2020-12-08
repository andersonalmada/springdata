package br.ufc.web.aula.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.aula.model.Book;
import br.ufc.web.aula.model.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findByBook(Book book, Sort sort);
}