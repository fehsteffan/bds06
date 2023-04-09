package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	
	List<Review> findByUser(User user);
	

}
