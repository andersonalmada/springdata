package br.ufc.web.aula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.web.aula.model.Book;
import br.ufc.web.aula.model.Page;
import br.ufc.web.aula.model.User;
import br.ufc.web.aula.repository.BookRepository;
import br.ufc.web.aula.repository.PageRepository;
import br.ufc.web.aula.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PageRepository pageRepo;
	@Autowired
	BookRepository bookRepo;

	@RequestMapping(method = RequestMethod.GET, path="/book")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list = bookRepo.findAll();
		
		System.out.println(list);
		
		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		Book book = new Book("Java 101", "John Doe", "123456");

        // save the book
        bookRepo.save(book);

        // create and save new pages
        pageRepo.save(new Page(1, "Introduction contents", "Introduction", book));
        pageRepo.save(new Page(65, "Java 8 contents", "Java 8", book));
        pageRepo.save(new Page(95, "Concurrency contents", "Concurrency", book));
    
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<User> getUserByLogin(@RequestParam("login") String login) {
        return new ResponseEntity<User>(userService.getUserByLogin(login), HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user.getLogin(), user.getPassword()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(id, user.getLogin(), user.getPassword()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		if (userService.removeUser(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
