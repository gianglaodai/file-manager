package com.leo.prj.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.leo.prj.enumeration.Popup;
import com.leo.prj.service.popup.PopupService;

@CrossOrigin
@RestController
@RequestMapping("/popup")
public class PopupController {

	@Autowired
	private PopupService popupService;

	@GetMapping("/getAll/{catalog}")
	public ResponseEntity<List<FileInfo>> getAll(@PathVariable int catalog) {
		return ResponseEntity.ok(popupService.getAllByCatalog(catalog,
				Stream.of(Popup.values()).map(p -> p.getValue()).collect(Collectors.toList())));
	}

	@GetMapping("/load/{catalog}")
	public ResponseEntity<EditorPageData> load(@PathVariable int catalog, @RequestParam String fileName) {
		return ResponseEntity.ok(popupService.loadFromCatalog(catalog, fileName).get());
	}

	@PostMapping("/save/{catalog}")
	public ResponseEntity<Boolean> save(@PathVariable int catalog, @RequestBody EditorPageData data) {
		return ResponseEntity.ok(popupService.saveToCatalog(catalog, data));
	}
}
