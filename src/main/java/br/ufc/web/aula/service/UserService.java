package br.ufc.web.aula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.web.aula.model.User;
import br.ufc.web.aula.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User addUser(String login, String password) {
		User user = new User(login, password);
		return userRepo.save(user);
	}
	
	public boolean removeUser(Integer id) {
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}
	
	public User getUserByLogin(String login) {
		return userRepo.findFirstByLogin(login);
	}
	
	public User updateUser(Integer id, String login, String password) {
		User userAux = userRepo.findById(id).get();
		
		if(userAux != null) {
			userAux.setLogin(login);;
			userAux.setPassword(password);
			userRepo.save(userAux);			
		}
		
		return userAux;
	}
}