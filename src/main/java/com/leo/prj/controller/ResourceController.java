package com.leo.prj.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leo.prj.constant.CommonConstant;
import com.leo.prj.util.FileResourcePath;

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
