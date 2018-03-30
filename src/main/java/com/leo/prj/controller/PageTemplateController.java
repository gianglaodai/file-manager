package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.pagetemp.PageTemplateService;

@CrossOrigin
@RestController
public class PageTemplateController {

	@Autowired
	private PageTemplateService pageTemplateService;

	@GetMapping("/templates")
	public List<FileInfo> getPageTemplates() {
		return this.pageTemplateService.getTemplates();
	}

}
