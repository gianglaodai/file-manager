package com.leo.prj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.page.PageService;

@CrossOrigin
@RestController
public class PageController {
	@Autowired
	private PageService pageService;

	@PostMapping("/savePage")
	public ResponseEntity<Boolean> savePage(@RequestBody EditorPageData data) {
		return ResponseEntity.ok(this.pageService.savePage(data));
	}

	@GetMapping("/getPages")
	public ResponseEntity<List<FileInfo>> getPages() {
		return ResponseEntity.ok(this.pageService.getPages());
	}

	@GetMapping("/loadPage")
	public ResponseEntity<EditorPageData> loadPage(@RequestParam String pageName) {
		final Optional<EditorPageData> pageData = this.pageService.loadPage(pageName);
		if (pageData.isPresent()) {
			return ResponseEntity.ok(pageData.get());
		}
		return ResponseEntity.notFound().build();
	}
}
