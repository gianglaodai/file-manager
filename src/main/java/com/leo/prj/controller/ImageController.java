package com.leo.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.ImageInfo;
import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.FileInfoService;
import com.leo.prj.service.FileService;

@CrossOrigin
@RestController
public class ImageController {
	@Autowired
	private FileService imageService;

	@Autowired
	private FileInfoService<ImageInfo> imageInfoService;

	@PostMapping("/images")
	public ResponseEntity<UploadFilesResult> uploadImages(@RequestParam("file") final List<MultipartFile> files) {
		return ResponseEntity.ok(this.imageService.upload(files));
	}

	@GetMapping("/images")
	public ResponseEntity<List<ImageInfo>> getImages() {
		return ResponseEntity.ok(this.imageInfoService.getFileInfos());
	}

	@DeleteMapping("/images")
	public ResponseEntity<Integer> deleteImage(@RequestParam List<String> fileNames) {
		return ResponseEntity.ok(this.imageService.deleteFiles(fileNames));
	}
}
