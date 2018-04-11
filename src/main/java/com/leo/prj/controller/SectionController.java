package com.leo.prj.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

	@GetMapping("/getAll/{catalog}")
	public ResponseEntity<List<FileInfo>> getSections(@PathParam("catalog") String catalog) {
		return ResponseEntity.ok(this.sectionService.getAllByCatalog(catalog));
	}

	@GetMapping("/load/{catalog}")
	public ResponseEntity<EditorPageData> load(@PathParam("catalog") String catalog, @RequestParam String fileName) {
		return ResponseEntity.ok(this.sectionService.loadFromCatalog(catalog, fileName).get());
	}

	@GetMapping("/saveSection/{catalog}")
	public ResponseEntity<Boolean> save(@PathParam("catalog") String catalog, @RequestParam EditorPageData data) {
		return ResponseEntity.ok(this.sectionService.saveToCatalog(catalog, data));
	}
}
