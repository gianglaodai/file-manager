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
import com.leo.prj.bean.RenameBody;
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
	public ResponseEntity<Boolean> save(@RequestBody EditorPageData data, @RequestParam String product,
			@RequestParam boolean publish) {
		return ResponseEntity.ok(pageService.save(data, product, publish));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getAll(@RequestParam String product) {
		return ResponseEntity.ok(pageService.getAll(product));
	}

	@GetMapping("/load")
	public ResponseEntity<EditorPageData> load(@RequestParam String pageName, @RequestParam String product,
			@RequestParam boolean publish) {
		final Optional<EditorPageData> pageData = pageService.load(pageName, product, publish);
		if (pageData.isPresent()) {
			return ResponseEntity.ok(pageData.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestParam String pageName, @RequestParam String templateName,
			@RequestParam int catalog, @RequestParam String product) {
		final Optional<EditorPageData> template = templateService.loadFromCatalog(catalog, templateName);
		final EditorPageData page = new EditorPageData();
		page.setPageName(pageName);
		template.ifPresent(data -> {
			page.setJsonContent(data.getJsonContent());
			page.setHtmlContent(data.getHtmlContent());
		});
		pageService.save(page, product);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(@RequestParam List<String> fileNames, @RequestParam String product) {
		return ResponseEntity.ok(pageService.delete(fileNames, product));
	}

	@GetMapping("/preview")
	public String preview(@RequestParam String pageName, @RequestParam String product, @RequestParam boolean publish) {
		return pageService.preview(pageName, product, publish);
	}

	@GetMapping("/publish")
	public ResponseEntity<Boolean> publish(@RequestParam String pageName, @RequestParam String product) {
		return ResponseEntity.ok(pageService.publish(pageName, product));
	}

	@PutMapping("/rename")
	public ResponseEntity<Boolean> rename(@RequestBody RenameBody renameBody, @RequestParam String product) {
		return ResponseEntity.ok(pageService.rename(renameBody.getPageName(), renameBody.getNewPageName(), product,
				renameBody.isPublish()));
	}
}
