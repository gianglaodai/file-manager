package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.template.TemplateService;

@CrossOrigin
@RestController
public class TemplateController {

	@Autowired
	private TemplateService pageTemplateService;

	@GetMapping("/templates")
	public List<FileInfo> getTemplates() {
		return this.pageTemplateService.getAll();
	}

	@GetMapping("/loadTemplate")
	public EditorPageData loadTemplate(@RequestParam String templateName) {
		return this.pageTemplateService.load(templateName).get();
	}
}
