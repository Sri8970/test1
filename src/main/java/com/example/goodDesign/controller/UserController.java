package com.example.goodDesign.controller;

import com.example.goodDesign.entity.User;
import com.example.goodDesign.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	// ✅ GET /api/v1/users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = service.getAllUsers();
		return ResponseEntity.ok(users); // 200
	}

	// ✅ GET /api/v1/users/{id}
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = service.getUserById(id);
		return ResponseEntity.ok(user); // 200
	}

	// ✅ POST /api/v1/users
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = service.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // 201
	}

	// ✅ PUT /api/v1/users/{id}
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {

		User updatedUser = service.updateUser(id, user);
		return ResponseEntity.ok(updatedUser); // 200
	}

	// ✅ DELETE /api/v1/users/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build(); // 204
	}

	// ✅ Nested resource: GET /api/v1/users/{id}/orders
	@GetMapping("/{id}/orders")
	public ResponseEntity<String> getUserOrders(@PathVariable Long id) {
		service.getUserById(id); // validate existence
		return ResponseEntity.ok("Orders for user " + id);
	}
}