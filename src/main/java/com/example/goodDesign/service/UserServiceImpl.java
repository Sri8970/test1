package com.example.goodDesign.service;

import com.example.goodDesign.entity.User;
import com.example.goodDesign.exception.UserNotFoundException;
import com.example.goodDesign.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User getUserById(Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public User createUser(User user) {

		if (repository.existsByName(user.getName())) {
			throw new IllegalArgumentException("User already exists");
		}

		return repository.save(user);
	}

	public User updateUser(Long id, User updatedUser) {

		User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		existingUser.updateName(updatedUser.getName());

		return repository.save(existingUser);
	}

	public void deleteUser(Long id) {

		if (!repository.existsById(id)) {
			throw new UserNotFoundException(id);
		}

		repository.deleteById(id);
	}
}