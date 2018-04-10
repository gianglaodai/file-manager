package com.leo.prj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.page.PageService;
import com.leo.prj.service.template.TemplateService;

@CrossOrigin
@RestController
@RequestMapping("/page")
public class PageController {
	@Autowired
	private PageService pageService;

	@Autowired
	private TemplateService templateService;

	@PostMapping("/save")
	public ResponseEntity<Boolean> save(@RequestBody EditorPageData data) {
		return ResponseEntity.ok(this.pageService.save(data));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getAll() {
		return ResponseEntity.ok(this.pageService.getAll());
	}

	@GetMapping("/load")
	public ResponseEntity<EditorPageData> load(@RequestParam String pageName) {
		final Optional<EditorPageData> pageData = this.pageService.load(pageName);
		if (pageData.isPresent()) {
			return ResponseEntity.ok(pageData.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestParam String pageName, @RequestParam String templateName) {
		final Optional<EditorPageData> template = this.templateService.load(templateName);
		final EditorPageData page = new EditorPageData();
		page.setPageName(pageName);
		template.ifPresent(data -> {
			page.setJsonContent(data.getJsonContent());
			page.setHtmlContent(data.getHtmlContent());
		});
		this.pageService.save(page);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(@RequestParam List<String> fileNames) {
		return ResponseEntity.ok(this.pageService.delete(fileNames));
	}
}
