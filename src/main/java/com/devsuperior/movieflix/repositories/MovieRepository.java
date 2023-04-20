package com.devsuperior.movieflix.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	
	@Query("SELECT obj FROM Movie obj INNER JOIN obj.genre genrs WHERE "
			+ "(:genre IS NULL OR :genre IN genrs)")
	Page<Movie> find(Genre genre, Pageable pageable);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	

}
