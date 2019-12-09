package io.github.xaphira.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("userService")
public class UserImplService implements UserDetailsService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.loadByUsername(username.toLowerCase());
		if (user == null) throw new UsernameNotFoundException("User '" + username + "' not found.");
		return user;
	}

}
