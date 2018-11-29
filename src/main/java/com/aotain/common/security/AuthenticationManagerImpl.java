package com.aotain.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotain.common.authService.UserserviceStub.ServiceReturnDTO;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.OpType;

@Transactional
@Repository
@Service(value="AuthenticationService")
public class AuthenticationManagerImpl implements AuthenticationManager{

	  public Authentication authenticate(Authentication auth)  {
		  	try{
		  		List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
		  		Userbean userInfo = (Userbean) auth.getPrincipal();
				UserDetail userDetail = new UserDetail(userInfo, userInfo.getUserId(), userInfo.getUserName(), userInfo.getPassword(), true, true, true, true, AUTHORITIES);
				ServiceReturnDTO serviceReturnDTO = userInfo.getServiceReturnDTO();
				if(serviceReturnDTO.getUserprivilegexml()!=""){
					List<String> userActionList = LoginInfoUtil.privilegeXML2List(serviceReturnDTO.getUserprivilegexml());
					for(int i=0;i<userActionList.size();i++){
						AUTHORITIES.add(new SimpleGrantedAuthority(("ROLE_"+userActionList.get(i)).toUpperCase()));
					}
			    }
				return new UsernamePasswordAuthenticationToken(userDetail,
				auth.getCredentials(), AUTHORITIES);
			}	catch(AuthenticationException e){
				e.printStackTrace();
				throw new BadCredentialsException("Bad Credentials");
			}	catch(Exception e){
				e.printStackTrace();
				throw new BadCredentialsException("Bad Credentials");
			}
	  }
}

