package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.section.SectionService;

@CrossOrigin
@RestController
public class SectionController {
	@Autowired
	private SectionService sectionService;

	@GetMapping("/sections")
	public List<FileInfo> getSections() {
		return this.sectionService.getAll();
	}

	@GetMapping("/loadSection")
	public EditorPageData loadTemplate(@RequestParam String sectionName) {
		return this.sectionService.load(sectionName).get();
	}
}
