package com.leo.prj.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.constant.CommonConstant;
import com.leo.prj.util.FileResourcePath;

@CrossOrigin
@RestController
public class ResourceController {
	@GetMapping("/" + CommonConstant.URLConstant.RESOUCE_PATH_IMG + "/{user}/{file:.+}")
	public ResponseEntity<Resource> serveImage(@PathVariable("user") String user, @PathVariable String file)
			throws Exception {
		final Resource image = new UrlResource(
				FileResourcePath.createUploadImagePath(user).addPath(file).getPath().toUri());
		return ResponseEntity.ok(image);
	}
}
