package com.leo.prj.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.service.page.PageService;

@CrossOrigin
@RestController
public class PageController {
	@Autowired
	private PageService pageService;

	@PostMapping("/savePage")
	public ResponseEntity<Boolean> savePage(@RequestParam EditorPageData data) {
		return ResponseEntity.ok(Boolean.TRUE);
	}

	@GetMapping("/getPages")
	public ResponseEntity<List<String>> getPages() {
		return ResponseEntity.ok(Collections.emptyList());
	}

	@GetMapping("/loadPage")
	public ResponseEntity<EditorPageData> loadPage(@RequestParam String pageName) {
		return ResponseEntity.ok(new EditorPageData());
	}
}
