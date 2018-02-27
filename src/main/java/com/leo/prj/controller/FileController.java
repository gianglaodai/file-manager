package com.leo.prj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.service.UploadService;

@RestController
public class FileController {
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/uploadFiles")
	public ResponseEntity<UploadFilesResult> uploadFiles(@RequestParam("files")List<MultipartFile> uploadFiles){
		return ResponseEntity.ok(uploadService.uploadFiles(uploadFiles));
	}
	
	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFilesResult> uploadFiles(@RequestParam("file")MultipartFile uploadFile) throws Exception{
		return ResponseEntity.ok(uploadService.uploadFile(uploadFile));
	}
}
