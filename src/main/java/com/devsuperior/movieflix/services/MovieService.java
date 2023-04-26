package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.dto.MovieDTO;
import com.devsuperior.movieflix.entities.dto.MovieDTO2;
import com.devsuperior.movieflix.entities.dto.MovieReviewDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;	
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private AuthService  authService;	
	
	
	
	@Transactional(readOnly = true)
	public List<MovieReviewDTO> findByMovie(Long movieId) {	
		
		authService.validateSelfOrMember(movieId);
		
		try {
		Movie movie = movieRepository.getOne(movieId);
		List<Review> list = reviewRepository.findByMovie(movie);
		return list.stream().map(x -> new MovieReviewDTO(x)).collect(Collectors.toList());
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + movieId);
		}
	}
	
	
			
	@Transactional(readOnly = true)
	public List<MovieReviewDTO> findAllMovieGenre(Long id) {
		authService.validateSelfOrMember(id);
		
		List<Review> list = reviewRepository.findAll();
		return list.stream().map(x -> new MovieReviewDTO(x)).collect(Collectors.toList());
	}
			
	
	
	@Transactional(readOnly = true)	
	public Page<MovieDTO2> pagedAllpage(Long genreId, Pageable pageable) {	
		
		authService.validateSelfOrMember(genreId);
				
		Genre genre = (genreId == 0) ? null :  genreRepository.getOne(genreId);			
		Page<Movie> page = movieRepository.find(genre, pageable); 		
		return page.map(x -> new MovieDTO2(x));			
		
	}	
	
	
	
	@Transactional(readOnly = true)	
	public Page<MovieDTO2> pagedAll(Pageable pageable) {
				
		Page<Movie> page = movieRepository.findAll(pageable); 		
		return page.map(x -> new MovieDTO2(x));			
		
	}	
	
		
	@Transactional(readOnly = true)	
	public MovieDTO findById(Long id) {	
		
		authService.validateSelfOrMember(id);
		
		Optional<Movie> obj = movieRepository.findById(id);			
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));		
		return new MovieDTO(entity);
		
	}

}
