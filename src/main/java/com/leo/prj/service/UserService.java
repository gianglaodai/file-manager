package com.leo.prj.service;

import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.util.FilePathUtil;

@Service
public class UserService {
	@Autowired
	private HttpServletRequest request;

	public String getCurrentUser() {
		return this.request.getParameter("user");
	}

	public Path getCurrentUserDirectory() {
		return this.getCurrentUserDirectory(this.getCurrentUser());
	}

	public Path getCurrentUserDirectory(String user) {
		return FilePathUtil.createUploadUserPath().add(user).getPath();
	}
}
