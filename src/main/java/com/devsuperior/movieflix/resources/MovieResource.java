package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.entities.dto.MovieDTO;
import com.devsuperior.movieflix.entities.dto.MovieDTO2;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	
	@Autowired
	private MovieService service;		
	
	
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO2>> pagedAll(Long genreId, Pageable pageable) {		
		Page<MovieDTO2> list = service.pagedAllpage(genreId, pageable);		
		return ResponseEntity.ok().body(list);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
		
		
		
	}	

}
