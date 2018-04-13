package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.template.TemplateService;

@CrossOrigin
@RestController
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private TemplateService templateService;

	@GetMapping("/getAll/{catalog}")
	public ResponseEntity<List<FileInfo>> getAll(@PathVariable int catalog) {
		return ResponseEntity.ok(this.templateService.getAllByCatalog(Integer.valueOf(catalog)));
	}

	@GetMapping("/load/{catalog}")
	public ResponseEntity<EditorPageData> load(@PathVariable int catalog, @RequestParam String fileName) {
		return ResponseEntity.ok(this.templateService.loadFromCatalog(catalog, fileName).get());
	}

	@PostMapping("/save/{catalog}")
	public ResponseEntity<Boolean> save(@PathVariable int catalog, @RequestBody EditorPageData data) {
		return ResponseEntity.ok(this.templateService.saveToCatalog(catalog, data));
	}

	@GetMapping("/preview/{catalog}")
	public String preview(@PathVariable int catalog, @RequestParam String fileName) {
		return this.templateService.preview(fileName, catalog);
	}
}
