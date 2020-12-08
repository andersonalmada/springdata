package br.ufc.web.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.aula.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findFirstByLogin(String login);
}
