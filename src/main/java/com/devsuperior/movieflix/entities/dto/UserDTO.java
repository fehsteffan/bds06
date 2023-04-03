package com.devsuperior.movieflix.entities.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class UserDTO implements Serializable  {	
	private static final long serialVersionUID = 1L;
	
	
	
	private Long id;
	
	@NotEmpty(message = "Campo obrigatorio")
	private String name;	
	
	@Email(message = "Favor entrar com email valido")
	private String email;
	
	private List<ReviewDTO> reviews = new ArrayList<>();
	
	
	public UserDTO() {		
	}


	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	
	public UserDTO(User entity) {		
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
	}

	
	public UserDTO(User entity, Set<Review> reviews) {
		this(entity);
		reviews.forEach(cat -> this.reviews.add(new ReviewDTO(cat)));
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<ReviewDTO> getReviews() {
		return reviews;
	}


	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}		
	
}
