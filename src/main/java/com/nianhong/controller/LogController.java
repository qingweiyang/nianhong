package com.nianhong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.User;
import com.nianhong.service.UserService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.Constants;
import com.nianhong.util.LoginInf;

/**
 * 登入，登出控制器
 * @author yqw
 *
 */
@Controller
@RequestMapping(value={"/taskRoom", "/saler", "/login"})
public class LogController {

	private UserService userService = ServiceHelper.getUserService();
	
	@RequestMapping("login.do")
	@ResponseBody
	public String login(HttpServletRequest request, String username, String password){
		User user = userService.selectByUsernameAndPassword(username, password);
		if(null != user) {
			//登入成功
			request.getSession().setAttribute(Constants.USER, user.getUsername());
			System.out.println("sss...."+request.getSession().getAttribute(Constants.USER));
		}
		return "success";
	}
	
	@RequestMapping("logout.do")
	@ResponseBody
	public String login(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.USER);
		LoginInf.username = null;
		return "success";
	}
}
