package com.leo.prj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
	@RequestMapping("/savePage")
	public ResponseEntity<Boolean> savePage(@RequestParam("data") String data,@RequestParam("user")String user) {
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
