package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getAll() {
		return ResponseEntity.ok(this.templateService.getAll());
	}

	@GetMapping("/load")
	public ResponseEntity<EditorPageData> load(@RequestParam String templateName) {
		return ResponseEntity.ok(this.templateService.load(templateName).get());
	}

	@PostMapping("/save/:catalog")
	public ResponseEntity<Boolean> save(@RequestBody EditorPageData data) {
		return ResponseEntity.ok(this.templateService.save(data));
	}
}
