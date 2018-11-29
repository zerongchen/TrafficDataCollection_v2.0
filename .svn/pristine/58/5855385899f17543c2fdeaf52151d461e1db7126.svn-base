package com.aotain.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aotain.common.authService.UserserviceStub;
import com.aotain.common.authService.UserserviceStub.*;
import com.aotain.common.security.UserDetail;
import com.aotain.common.security.Userbean;
import com.aotain.tdc.annotation.ClogAop;
import com.aotain.tdc.constant.OpType;

public class LoginInfoUtil {
	private static final int deployid = LocalConfig.getInstance().getDeployid();
	public static void main(String[] args) throws Exception{
		ServiceReturnDTO cdr = CheckUserDetailLogin("admin",MD5Util.passwordEncoder("123456"),"123123");
		System.out.println(cdr.getUserprivilegexml());
	}
	
	public static String[] getMenu(String requestUrl){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ctx = request.getContextPath();
		StringBuilder headMenu = new StringBuilder();
		StringBuilder leftMenu = new StringBuilder();
		String firstUrl = null;
		try {
			File file = new File(LoginInfoUtil.class.getResource("/").getPath().replaceAll("%20", " ") + "/sysmenu.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			
			Element sonNode = null;
			Element parentNode = null;
			if(requestUrl != null && requestUrl.length() > 0){
				@SuppressWarnings("unchecked")
				List<Element> targetNodes = doc.selectNodes("//*[ends-with(@url,'"+requestUrl.replaceFirst("^"+ctx, "")+"')]");
				if(targetNodes != null && targetNodes.size() > 0){
					Element targetNode = targetNodes.get(0);
					if(targetNode.getPath().split("/").length == 3){	//一级目录
						@SuppressWarnings("unchecked")
						List<Element> sonNodes = targetNode.selectNodes(".//*[@url!='']");
						if(sonNodes != null) sonNode = sonNodes.get(0);
						parentNode = targetNode;
					} else if(targetNode.getPath().split("/").length == 5){	//三级目录
						parentNode = targetNode.getParent().getParent();
						sonNode = targetNode;
					}
				}
			}else{
				@SuppressWarnings("unchecked")
				List<Element> targetNodes = doc.selectNodes("//*[@url!='']");
				Element targetNode = targetNodes.get(1);
				if(targetNode.getPath().split("/").length == 3){	//一级目录
					@SuppressWarnings("unchecked")
					List<Element> sonNodes = targetNode.selectNodes(".//*[@url!='']");
					if(sonNodes != null) sonNode = sonNodes.get(0);
					parentNode = targetNode;
				} else if(targetNode.getPath().split("/").length == 5){	//三级目录
					parentNode = targetNode.getParent().getParent();
					sonNode = targetNode;
				}
			}
			
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> firstNodes = (List<Element>)root.elements("FirstMenu");
			headMenu.append("<div class=\"nav fl\">");
			headMenu.append("<ul>");
			leftMenu.append("<div class=\"frame\">");
			for(Iterator<Element> firstNode = firstNodes.iterator(); firstNode.hasNext();){
				Element menuItem = firstNode.next();
				if(LoginInfoUtil.CheckHeadMenu(menuItem.attribute("name").getText())){
					@SuppressWarnings("unchecked")
					List<Element> secondNodes = (List<Element>) menuItem.elements("SecondMenu");
					for(Iterator<Element> secondNode = secondNodes.iterator(); secondNode.hasNext();){
						Element menuItem2 = secondNode.next();
						if(LoginInfoUtil.CheckMenu(menuItem2.attribute("name").getText()) && menuItem2.getParent().equals(parentNode) ){
							leftMenu.append("<ul>");
							leftMenu.append("<li class=\"title\"><i class=\""+(menuItem2.attribute("name")!=null?menuItem2.attribute("icon").getText():"icon_a")+"\"></i> <span>"+menuItem2.attribute("name").getText()+"</span><i class=\"fr icon_e\"></i></li>");
							
							@SuppressWarnings("unchecked")
							List<Element> thirdNodes = (List<Element>) menuItem2.elements("ThirdMenu");
							for(Iterator<Element> thirdNode = thirdNodes.iterator(); thirdNode.hasNext();){
								Element menuItem3 = thirdNode.next();
								if(LoginInfoUtil.CheckMenu(menuItem3.attribute("name").getText())){
									if(menuItem3.attribute("url").getText().length() > 0 && firstUrl == null){
										firstUrl = menuItem3.attribute("url").getText();
									}
									leftMenu.append("<li class=\""+( (menuItem3.equals(sonNode))?"check":"")+"\"><a target=\"_parent\" href=\""+ctx+menuItem3.attribute("url").getText()+"\">"+menuItem3.attribute("name").getText()+"</a></li>");
								}
							}
							leftMenu.append("</ul>");
						}
					}
					if(menuItem.attribute("url").getText().length() > 0 && firstUrl == null && requestUrl == null){
						firstUrl = menuItem.attribute("url").getText();
					}

					headMenu.append("<li class=\""+(menuItem.equals(parentNode)?"check":"")+"\"><a href=\""+ctx+menuItem.attribute("url").getText()+"\"><p><i class=\""+(menuItem.attribute("name")!=null?menuItem.attribute("icon").getText():"icon_a")+"\"></i></p><p>"+menuItem.attribute("name").getText()+"</p></a></li>");
				}
			}
			leftMenu.append("</div>");
			headMenu.append("</ul>");
			headMenu.append("</div>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[]{headMenu.toString(), leftMenu.toString(), firstUrl};
	}
	
	public static boolean CheckHeadMenu(String name){
		Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
		List<String> menuList = userbean.getMenuList();
		for(String menu:menuList){
			if(menu.split("-").length > 1 && menu.split("-")[0].equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean CheckMenu(String name){
		Userbean userbean = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
		List<String> menuList = userbean.getMenuList();
		for(String menu:menuList){
			if(menu.split("-").length > 1 && menu.split("-")[1].equals(name)){
				return true;
			}else if(menu.equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public static UserserviceStub.ServiceReturnDTO CheckUserDetailLogin(String userName,String pw, String ip) throws Exception{
		UserserviceStub us = new UserserviceStub();
		UserserviceStub.CheckUserDetailLogin userInfo = new UserserviceStub.CheckUserDetailLogin();
		userInfo.setIn0(userName);
		userInfo.setIn1(pw);
		userInfo.setIn2(deployid);
		userInfo.setIn3(ip);
		
		CheckUserDetailLoginResponse response = us.checkUserDetailLogin(userInfo);
		UserserviceStub.ServiceReturnDTO dto = response.getOut();
		return dto;
	}
	public static List<String> menuXML2List(String privilege){
		List<String> list = new ArrayList<String>();
		try {
			String xmlStr = privilege;
			Document doc;
			doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			Element foot = root.element("UserMenu");
			for(Iterator<?> i_pe = foot.elementIterator();i_pe.hasNext();){
				Element eoot = (Element) i_pe.next();
				String mi = eoot.attribute("menuitemname").getText();
				list.add(mi.trim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return list;
	}
	public static List<String> privilegeXML2List(String privilege){
		List<String> list = new ArrayList<String>();
		try {
			String xmlStr = privilege;
			Document doc;
			doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			Element foot = root.element("UserAction");
			for(Iterator<?> i_pe = foot.elementIterator();i_pe.hasNext();){
				Element eoot = (Element) i_pe.next();
				list.add(eoot.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Userbean userInfoXML2Model(String userInfo){
		Userbean user = new Userbean();
		try {
			String xmlStr = userInfo;
			Document doc;
			doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			for(Iterator<?> i_pe = root.elementIterator();i_pe.hasNext();i_pe.next()){
				user.setUserId(Long.valueOf(root.element("userid").getData().toString()));
				user.setUserType(Integer.valueOf(root.element("usertype").getData().toString()));
				user.setFullName(root.element("fullname").getData().toString());
				user.setUserDescription(root.element("userdesc").getData().toString());
				user.setProvinceId(Integer.valueOf(root.element("city").getData().toString().split(",")[0]));
				user.setCityId(Integer.valueOf(root.element("area").getData().toString().split(",")[0]));
				user.setEmail(root.element("email").getData().toString());
				user.setMobile(root.element("phone").getData().toString());
				user.setStatus(Integer.valueOf(root.element("status").getData().toString()));
				user.setDepartment(root.element("department").getData().toString());
				user.setUserlevel(Integer.valueOf(root.element("userlevel").getData().toString()));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return user;
	}
}
