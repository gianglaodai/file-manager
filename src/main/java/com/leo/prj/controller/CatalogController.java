package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.Catalog;
import com.leo.prj.service.CatalogService;

@CrossOrigin
@RestController
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/template")
	public List<Catalog> getTemplateCatalogs() {
		return this.catalogService.getTemplateCatalog();
	}

	@GetMapping("/section")
	public List<Catalog> getSectionCatalogs() {
		return this.catalogService.getSectionCatalog();
	}

	@GetMapping("/popup")
	public List<Catalog> getPopupCatalogs() {
		return this.catalogService.getPopupCatalog();
	}
}
