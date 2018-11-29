package com.aotain.common.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value="userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
		
	@Override
	public UserDetails loadUserByUsername(String userName) {
		User userdetail = null;
		return userdetail;
	}

}
