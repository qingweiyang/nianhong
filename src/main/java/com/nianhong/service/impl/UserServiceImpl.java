package com.nianhong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.UserDao;
import com.nianhong.model.User;
import com.nianhong.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDAO;

	@Override
	public User selectByUsernameAndPassword(String username, String password) {
		return userDAO.selectByUsernameAndPassword(username, password);
	}

}
