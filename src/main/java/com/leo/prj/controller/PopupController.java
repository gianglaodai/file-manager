package com.leo.prj.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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
import com.leo.prj.service.popup.PopupService;

@CrossOrigin
@RestController
@RequestMapping("/popup")
public class PopupController {
	public class SectionController {
		@Autowired
		private PopupService popupService;

		@GetMapping("/getAll/{catalog}")
		public ResponseEntity<List<FileInfo>> getAll(@PathParam("catalog") String catalog) {
			return ResponseEntity.ok(this.popupService.getAllByCatalog(catalog));
		}

		@GetMapping("/load/{catalog}")
		public ResponseEntity<EditorPageData> load(@PathParam("catalog") String catalog,
				@RequestParam String fileName) {
			return ResponseEntity.ok(this.popupService.loadFromCatalog(catalog, fileName).get());
		}

		@PostMapping("/save/{catalog}")
		public ResponseEntity<Boolean> save(@PathParam("catalog") String catalog, @RequestBody EditorPageData data) {
			return ResponseEntity.ok(this.popupService.saveToCatalog(catalog, data));
		}
	}
}
