package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.section.SectionService;

@CrossOrigin
@RestController
@RequestMapping("/section")
public class SectionController {
	@Autowired
	private SectionService sectionService;

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getSections() {
		return ResponseEntity.ok(this.sectionService.getAll());
	}

	@GetMapping("/load")
	public ResponseEntity<EditorPageData> load(@RequestParam String sectionName) {
		return ResponseEntity.ok(this.sectionService.load(sectionName).get());
	}

	@GetMapping("/saveSection")
	public ResponseEntity<Boolean> save(@RequestParam EditorPageData data) {
		return ResponseEntity.ok(this.sectionService.save(data));
	}
}
