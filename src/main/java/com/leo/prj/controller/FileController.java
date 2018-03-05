package com.leo.prj.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.UploadService;
import com.leo.prj.util.FileResourcePath;

@RestController
public class FileController {
	@Autowired
	private UploadService uploadService;

	@PostMapping("/uploadImages")
	public ResponseEntity<UploadFilesResult> uploadFiles(@RequestParam("files")List<MultipartFile> uploadFiles, @RequestParam("user")String user){
		final File file = Paths.get(FileResourcePath.createUploadImagePath(user).getPath().toUri()).toFile();
		if(!file.exists()) {
			file.mkdirs();
		}
		return ResponseEntity.ok(this.uploadService.uploadFiles(uploadFiles, user));
	}

}
