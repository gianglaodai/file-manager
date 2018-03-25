package com.leo.prj.service.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.service.UserService;

@Service
public class PageService {
	@Autowired
	private UserService userService;
}
