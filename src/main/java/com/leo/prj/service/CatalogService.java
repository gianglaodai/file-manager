package com.leo.prj.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.leo.prj.bean.Catalog;
import com.leo.prj.enumeration.CatalogStructure;
import com.leo.prj.enumeration.Popup;
import com.leo.prj.enumeration.Section;
import com.leo.prj.enumeration.Template;

@Service
public class CatalogService {
	public List<Catalog> getTemplateCatalog() {
		return this.getCatalogs(Template.values());
	}

	public List<Catalog> getSectionCatalog() {
		return this.getCatalogs(Section.values());
	}

	public List<Catalog> getPopupCatalog() {
		return this.getCatalogs(Popup.values());
	}

	private List<Catalog> getCatalogs(CatalogStructure[] catalogs) {
		return Stream.of(catalogs).map(catalog -> new Catalog(catalog.getValue(), catalog.getLabel()))
				.collect(Collectors.toList());
	}
}
