package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.entities.dto.MovieDTO;
import com.devsuperior.movieflix.entities.dto.MovieDTO2;
import com.devsuperior.movieflix.entities.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	
	@Autowired
	private MovieService movieService;	
	
	@Autowired
	private ReviewService service;
	
	
	
	
	
	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findByMovie(@PathVariable Long movieId) {		
		List<ReviewDTO> list = service.findByMovie(movieId); 
		return ResponseEntity.ok(list);
	}	
	
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO2>> pagedByGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			 Pageable pageable) {	
		
		Page<MovieDTO2> list = movieService.pagedByGenre(genreId, pageable);		
		return ResponseEntity.ok().body(list);
	}	
	
		
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
		
			
	}	

}
