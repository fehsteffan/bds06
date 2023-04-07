package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.dto.ReviewDTO;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {	
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@Transactional(readOnly = true)	
	public List<ReviewDTO> findAll() {				
		List<Review> list = reviewRepository.findAll();  
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());		
		
	}	
	
	
	
	    @Transactional
		public ReviewDTO insert(ReviewDTO dto) {
		    Review entity = new Review();
	    	entity.setText(dto.getText()); 
	    	Movie movie = movieRepository.getOne(dto.getMovieId());
	    	entity.setMovie(movie);
	    	
	    	
	    	entity = reviewRepository.save(entity);
			return new ReviewDTO(entity);
	}	

}
