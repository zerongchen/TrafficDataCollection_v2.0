package com.aotain.common.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetail extends User implements Serializable {

	private static final long serialVersionUID = -2139433279922902369L;
	
	private Long userId;
	
	private Userbean account;
	
	public UserDetail(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public UserDetail(Userbean account, Long userId, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.account = account;
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Userbean getAccount() {
		return account;
	}

	public void setAccount(Userbean account) {
		this.account = account;
	}
	
}
