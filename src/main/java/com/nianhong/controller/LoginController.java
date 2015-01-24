package com.nianhong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nianhong.model.User;
import com.nianhong.service.UserService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.Constants;

@Controller
@RequestMapping("login")
public class LoginController {

	private UserService userService = ServiceHelper.getUserService();
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, String username, String password){
		User user = userService.selectByUsernameAndPassword(username, password);
		if(null != user) {
			//登入成功
			request.getSession().setAttribute(Constants.USER, user.getUsername());
		}
		return "index";
	}
	
}
