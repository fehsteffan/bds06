package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.entities.dto.UserDTO;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger Logger = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	
	
	@Transactional(readOnly = true)	
	public Page<UserDTO> paged (Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
			
		
	}	
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {			
		authService.validateSelfOrMember(id);	 	  	
	    Optional<User> obj = repository.findById(id);
	    User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
	    return new UserDTO(entity);
			  
	}	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		User user = repository.findByEmail(username);		
		if (user == null) {
			Logger.error("User not found" + username);
			throw new UsernameNotFoundException("Email not found");
			
		}	
		
		
		Logger.info("User found" + username);		
		return user;		
	}

}
