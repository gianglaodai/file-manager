package com.leo.prj.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.FileService;
import com.leo.prj.service.UploadService;
import com.leo.prj.util.FileResourcePath;

@CrossOrigin
@RestController
public class ImageController {
	@Autowired
	private UploadService uploadService;

	@Autowired
	private FileService imageService;

	@PostMapping("/images")
	public ResponseEntity<UploadFilesResult> uploadImages(@RequestParam("file") final List<MultipartFile> files,
			@RequestParam final String user) {
		this.checkAndCreateDirectory(user);
		return ResponseEntity.ok(this.uploadService.uploadImages(files, user));
	}

	@GetMapping("/images")
	public ResponseEntity<List<? extends FileInfo>> getImages(@RequestParam String user) {
		final boolean hasDirectory = this.checkAndCreateDirectory(user);
		return hasDirectory ? ResponseEntity.ok(this.imageService.getFiles(user))
				: ResponseEntity.ok(Collections.emptyList());
	}

	private boolean checkAndCreateDirectory(String user) {
		final File file = Paths.get(FileResourcePath.createUploadImagePath(user).getPath().toUri()).toFile();
		if (!file.exists()) {
			file.mkdirs();
			return false;
		}
		return true;
	}

	@DeleteMapping("/images")
	public ResponseEntity<Integer> deleteImage(@RequestParam List<String> fileNames, @RequestParam String user) {
		final boolean hasDirectory = this.checkAndCreateDirectory(user);
		return hasDirectory ? ResponseEntity.ok(this.imageService.deleteFiles(fileNames, user)) : ResponseEntity.ok(0);
	}
}
