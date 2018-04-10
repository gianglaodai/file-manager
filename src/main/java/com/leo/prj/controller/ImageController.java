package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.FileInfoService;
import com.leo.prj.service.FileService;

@CrossOrigin
@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private FileService imageService;

	@Autowired
	private FileInfoService<FileInfo> imageInfoService;

	@PostMapping("/save")
	public ResponseEntity<UploadFilesResult> save(@RequestParam("file") final List<MultipartFile> files) {
		return ResponseEntity.ok(this.imageService.upload(files));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FileInfo>> getAll() {
		return ResponseEntity.ok(this.imageInfoService.getAll());
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(@RequestParam List<String> fileNames) {
		return ResponseEntity.ok(this.imageService.deleteFiles(fileNames));
	}
}
