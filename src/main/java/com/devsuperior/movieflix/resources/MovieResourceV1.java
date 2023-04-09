package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.entities.dto.MovieReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "v1/movies")
public class MovieResourceV1 {
	
	
	@Autowired
	private MovieService service;		
	
	
	
	@GetMapping
	public ResponseEntity<List<MovieReviewDTO>> findAllMovieGenre( ) {
		List<MovieReviewDTO> list = service.findAllMovieGenre();		
		return ResponseEntity.ok().body(list);
	}		
	

}
