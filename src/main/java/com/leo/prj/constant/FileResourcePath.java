package com.leo.prj.constant;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileResourcePath {
	private static final String RESOURCES = "resources";
	private static final String RESOURCES_UPLOAD = FileResourcePath.RESOURCES.toString() + File.separator + "upload";

	private final String filePath;

	private FileResourcePath() {
		this.filePath = FileResourcePath.RESOURCES_UPLOAD;
	}

	private FileResourcePath(final String filePath) {
		this.filePath = filePath;
	}

	public static FileResourcePath createUploadPath() {
		return new FileResourcePath();
	}

	public FileResourcePath addPath(final String name) {
		return new FileResourcePath(this.filePath + File.separator + name);
	}

	public Path getPath() {
		return Paths.get(this.filePath);
	}
}
