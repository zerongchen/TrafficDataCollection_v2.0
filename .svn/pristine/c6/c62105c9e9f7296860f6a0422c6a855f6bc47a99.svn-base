package com.aotain.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.aotain.tdc.exception.NoAuthenticatedUserException;

public class SecurityUtils {

	public static boolean isAuthenticated(){
		try{
			getAuthenticatedUser();
			return true;
		}catch (NoAuthenticatedUserException e){
			return false;
		}
	}
	
	public static UserDetail getAuthenticatedUser() throws NoAuthenticatedUserException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object userDetails = authentication.getPrincipal();
			if (userDetails != null && userDetails instanceof UserDetails)
				return (UserDetail) userDetails;
		}
		throw new NoAuthenticatedUserException("No Authenticated User found.");
	}
	
	public static Long getUserId() {
		if (SecurityUtils.getAuthenticatedUser() != null) {
			return SecurityUtils.getAuthenticatedUser().getUserId();
		} else {
			return 0L;
		}
	}
}