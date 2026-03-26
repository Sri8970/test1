package com.example.goodDesign.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@Column(nullable = false)
	private String name;

	protected User() {
	}

	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void updateName(String name) {
		this.name = name;
	}
}