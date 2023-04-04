package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.entities.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;



@Service
public class GenreService {

	
	@Autowired
	private GenreRepository repository;
	
	@Autowired
	private AuthService authService;
	
	
	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		User user = authService.authenticated();
		user.getAuthorities();
		List<Genre> list = repository.findAll();
		return list.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
		
				
	}
	
}
