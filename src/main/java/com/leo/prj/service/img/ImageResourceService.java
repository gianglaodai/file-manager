package com.leo.prj.service.img;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.util.FilePathUtil;

@Service
public class ImageResourceService {
	private static final Logger logger = Logger.getLogger(ImageResourceService.class);

	@Autowired
	private ImageService imageService;

	public URI getImageUri(String user, String fileName) {
		Path imagePath = FilePathUtil.createUploadUserPath().add(user).add(ImageService.IMG_DIRECTORY).add(fileName)
				.getPath();
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

	private Path getShareImageDirectory() {
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
}
