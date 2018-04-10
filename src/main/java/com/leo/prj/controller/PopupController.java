package com.leo.prj.controller;

import java.util.List;

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

		@GetMapping("/getAll")
		public ResponseEntity<List<FileInfo>> getAll() {
			return ResponseEntity.ok(this.popupService.getAll());
		}

		@GetMapping("/load")
		public ResponseEntity<EditorPageData> load(@RequestParam String popupName) {
			return ResponseEntity.ok(this.popupService.load(popupName).get());
		}

		@PostMapping("/save")
		public ResponseEntity<Boolean> save(@RequestBody EditorPageData data) {
			return ResponseEntity.ok(this.popupService.save(data));
		}
	}
}
