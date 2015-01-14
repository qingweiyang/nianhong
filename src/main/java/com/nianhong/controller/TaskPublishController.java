package com.nianhong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.User;

@Controller
@RequestMapping("/")
public class TaskPublishController {

	@RequestMapping(value = "publish.do", method = RequestMethod.POST)
	@ResponseBody
	public void publishTask(HttpServletRequest request) {
		System.out.println(" ...K...  "+request.getParameter("username"));
	}
}
