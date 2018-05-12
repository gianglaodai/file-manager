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
		return getCatalogs(Template.values());
	}

	public List<Catalog> getSectionCatalog() {
		return getCatalogs(Section.values());
	}

	public List<Catalog> getPopupCatalog() {
		return getCatalogs(Popup.values());
	}

	private List<Catalog> getCatalogs(CatalogStructure[] catalogs) {
		return Stream.of(catalogs).sorted((c1, c2) -> c1.getOrder() - c2.getOrder())
				.map(catalog -> new Catalog(catalog.getValue(), catalog.getLabel())).collect(Collectors.toList());
	}
}
