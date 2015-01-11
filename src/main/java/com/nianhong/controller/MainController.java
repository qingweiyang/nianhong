package com.nianhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("login.do")
	public String index(String username,String password){
		if ("hello".equals(username)) {
            System.out.println(username +" 登录成功");
            return "loginSucess";//逻辑视图名       跳转页面默认为转发
        }
//		System.out.println(111);
		return "index";
	}
	
}
