package com.leo.prj.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.FileService;
import com.leo.prj.service.UploadService;
import com.leo.prj.util.FileResourcePath;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {
	@Autowired
	private UploadService uploadService;

	@Autowired
	private FileService imageService;

	@RequestMapping("/uploadImages")
	public ResponseEntity<UploadFilesResult> uploadImages(@RequestParam("file") final List<MultipartFile> files, @RequestParam final String user) {
		this.checkAndCreateDirectory(user);
		return ResponseEntity.ok(this.uploadService.uploadImages(files, user));
	}

	@RequestMapping("/getImages")
	public ResponseEntity<List<? extends FileInfo>> getImages(@RequestParam String user){
		this.checkAndCreateDirectory(user);
		return ResponseEntity.ok(this.imageService.getFiles(user));
	}

	private void checkAndCreateDirectory(String user) {
		final File file = Paths.get(FileResourcePath.createUploadImagePath(user).getPath().toUri()).toFile();
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
