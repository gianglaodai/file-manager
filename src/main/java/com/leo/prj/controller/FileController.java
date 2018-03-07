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

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.UploadService;
import com.leo.prj.util.FileResourcePath;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/uploadImages")
	public ResponseEntity<UploadFilesResult> uploadFiles(@RequestParam("file") final List<MultipartFile> files) {
		final File file = Paths.get(FileResourcePath.createUploadImagePath("giang").getPath().toUri()).toFile();
		if (!file.exists()) {
			file.mkdirs();
		}
		return ResponseEntity.ok(this.uploadService.uploadFiles(files, "giang"));
	}

}
