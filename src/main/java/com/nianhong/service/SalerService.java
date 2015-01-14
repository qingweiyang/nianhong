package com.nianhong.service;

import com.nianhong.model.Task;
import com.nianhong.model.User;

public interface SalerService {

	public boolean publishTask(User user, Task task);
}
