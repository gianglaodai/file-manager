package com.leo.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leo.prj.service.img.ImageResourceService;

@CrossOrigin
@RestController
public class ResourceController {
	public static final String IMAGE_URL = "/img/{user}/{fileName:.+}";

	public static final String TEMPLATE_THUMBNAIL = "/template/thumbnail/{fileName:.+}";

	@Autowired
	private ImageResourceService imageResourceService;

	@GetMapping(IMAGE_URL)
	public ResponseEntity<Resource> getImageResource(@PathVariable("user") String user, @PathVariable String fileName)
			throws Exception {
		final Resource image = new UrlResource(this.imageResourceService.getImageUri(user, fileName));
		return ResponseEntity.ok(image);
	}

	@GetMapping(TEMPLATE_THUMBNAIL)
	public ResponseEntity<Resource> getTemplateThumbnailResource(@PathVariable String fileName) throws Exception {
		final Resource image = new UrlResource(this.imageResourceService.getTemplateThumbnailResource(fileName));
		return ResponseEntity.ok(image);
	}
}
