package com.training.mars.elearning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.mars.elearning.model.User;
import com.training.mars.elearning.repository.UserRepository;

//@CrossOrigin(origins= "http://localhost:3000")
@CrossOrigin(origins= "https://momopeachyh.github.io")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User _user = repository.save(user);
	return new ResponseEntity<User>(_user, HttpStatus.CREATED);	
	
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateCredentials(@PathVariable("id")int id, @RequestBody User user){
		Optional<User> userData = repository.findById(id);
		if(userData.isPresent()) {
			User _user = userData.get();
			_user.setUserName(user.getUserName());
			_user.setPassword(user.getPassword());
			User updatedUser = repository.save(_user);
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteProduct(@PathVariable("id") int id) {
		repository.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}

