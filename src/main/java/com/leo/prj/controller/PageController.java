package com.leo.prj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping(value = "/save")
	public ResponseEntity<Boolean> save(@RequestBody EditorPageData data, @RequestParam String product) {
		return ResponseEntity.ok(this.pageService.save(data, product));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getAll(@RequestParam String product) {
		return ResponseEntity.ok(this.pageService.getAll(product));
	}

	@GetMapping("/load")
	public ResponseEntity<EditorPageData> load(@RequestParam String pageName, @RequestParam String product) {
		final Optional<EditorPageData> pageData = this.pageService.load(pageName, product);
		if (pageData.isPresent()) {
			return ResponseEntity.ok(pageData.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestParam String pageName, @RequestParam String templateName,
			@RequestParam int catalog, @RequestParam String product) {
		final Optional<EditorPageData> template = this.templateService.loadFromCatalog(catalog, templateName);
		final EditorPageData page = new EditorPageData();
		page.setPageName(pageName);
		template.ifPresent(data -> {
			page.setJsonContent(data.getJsonContent());
			page.setHtmlContent(data.getHtmlContent());
		});
		this.pageService.save(page, product);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(@RequestParam List<String> fileNames, @RequestParam String product) {
		return ResponseEntity.ok(this.pageService.delete(fileNames, product));
	}

	@GetMapping("/preview")
	public String preview(@RequestParam String pageName, @RequestParam String product) {
		return this.pageService.preview(pageName, product);
	}

	@PutMapping("/publish")
	public ResponseEntity<Boolean> publish(String pageName, @RequestParam String product) {
		return ResponseEntity.ok(this.pageService.publish(pageName, product));
	}
}
