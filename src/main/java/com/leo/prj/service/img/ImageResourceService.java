package com.leo.prj.service.img;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.service.pagetemp.PageTemplateService;
import com.leo.prj.util.FilePathUtil;

@Service
public class ImageResourceService {
	private static final Logger logger = Logger.getLogger(ImageResourceService.class);

	@Autowired
	private ImageService imageService;

	@Autowired
	private PageTemplateService pageTemplateService;

	public URI getImageUri(String user, String fileName) {
		Path imagePath = this.imageService.createFileUploadPath(fileName);
		imagePath = Files.exists(imagePath) ? imagePath : this.imageService.createFileDeletePath(fileName);
		if (Files.exists(imagePath)) {
			return imagePath.toUri();
		}
		return this.getNoImagePath().toUri();
	}

	private Path getNoImagePath() {
		final Path noImagePath = FilePathUtil.from(this.getShareImageDirectory()).add("no_image.png").getPath();
		if (!Files.exists(noImagePath)) {
			final String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAJYAAABkBAMAAACWddTDAAAALVBMVEX39/ebm5urq6vv7+/Ly8vk5OTDw8Ofn5+/v7+3t7ezs7Onp6fY2NjPz8/c3NwR2HHmAAABd0lEQVRYw+3SsUrDQBzH8R80tNEW4Z/eRW01JDoL6VR0SrrUSdo3aBE6t5OT0LyB3Ryb3cEiuAmtTyDFF/BNvJaeYpYrEsTh/x0OkoMPx/0PHMdxHMdxHMdxf1zfBwoOvuv6v7dEjtZ+mJ81m+ZntY7XlhU4kbYap6lbmNTUgRNyAbTr7wJY0pnJuqqHKyshOvyyTohaRD6aRHQHW20JWETUM1hRf6ysCkUWzbVVC2Ons6giGSOuYnFbSAW6L3iVJqvsKcsWQDzVlkTpCLsSqpISI5QFkjn2DkxWpa6schVoDLU1XBE7yrIGM4kgRFEgJSLHZGFy7aD0w/I31gURSYxCWALBVlbT25zrMWtNngclidn6XEEHgNGyHH1fWetGfUp9X71tLAR6jllrNLdGUs0RqzkKnLtmq6vfV9ZSP8mDrVaBolqfzFZRv/usZVPtUgBtuj8GluSGyCHbQz4lU8RD5NMHEb0hnyopPYDjOI7jOI7juP/XJ3B1TRmE/KufAAAAAElFTkSuQmCC";
			final byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			try {
				Files.write(noImagePath, imageBytes);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		return noImagePath;
	}

	public Path getShareImageDirectory() {
		final Path shareImageDirectory = Paths.get("/resources/share/img");
		if (!Files.exists(shareImageDirectory)) {
			try {
				Files.createDirectories(shareImageDirectory);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		return shareImageDirectory;
	}

	public URI getTemplateThumbnailResource(String fileName) {
		final Path imagePath = this.pageTemplateService.getFilePath(fileName);
		if (Files.exists(imagePath)) {
			return imagePath.toUri();
		}
		return this.getTemplateDefaultThumbnailPath().toUri();
	}

	private Path getTemplateDefaultThumbnailPath() {
		final Path noImagePath = FilePathUtil.from(this.getShareImageDirectory()).add("template_default.png").getPath();
		if (!Files.exists(noImagePath)) {
			final String base64Image = "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";
			final byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			try {
				Files.write(noImagePath, imageBytes);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		return noImagePath;
	}
}
