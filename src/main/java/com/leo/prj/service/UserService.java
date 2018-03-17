package com.leo.prj.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private HttpServletRequest request;

	public String getCurrentUser() {
		return this.request.getParameter("user");
	}
}
