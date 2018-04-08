package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.service.popup.PopupService;

@CrossOrigin
@RestController
public class PopupController {
	public class SectionController {
		@Autowired
		private PopupService popupService;

		@GetMapping("/popups")
		public List<FileInfo> getPopup() {
			return this.popupService.getAll();
		}

		@GetMapping("/loadPopup")
		public EditorPageData loadPopup(@RequestParam String popupName) {
			return this.popupService.load(popupName).get();
		}
	}
}
