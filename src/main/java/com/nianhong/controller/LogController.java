package com.nianhong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value={"*/"})
public class LogController {

	private UserService userService = ServiceHelper.getUserService();
	
	@RequestMapping(value="login.do", method = RequestMethod.POST)
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
	
	@RequestMapping(value="logout.do", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.USER);
		LoginInf.username = null;
		return "success";
	}
	
	@RequestMapping(value="loadUsername.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadUsername(){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("username", LoginInf.username);
		return res;
	}
}
