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
import com.leo.prj.service.pagetemp.PageTemplateService;

@CrossOrigin
@RestController
public class PageController {
	@Autowired
	private PageService pageService;

	@Autowired
	private PageTemplateService pageTemplateService;

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

	@PostMapping("/createPage")
	public ResponseEntity<Boolean> createPage(@RequestParam String pageName, @RequestParam String templateName){	
		Optional<EditorPageData> template = this.pageTemplateService.loadTemplate(templateName);
		EditorPageData page = new EditorPageData();
		page.setPageName(pageName);
		template.ifPresent(data-> page.setJsonContent(data.getJsonContent()));
		this.pageService.savePage(page);
		return ResponseEntity.ok(true);
	}
}
