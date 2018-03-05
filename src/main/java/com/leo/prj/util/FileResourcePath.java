package com.leo.prj.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileResourcePath {
	private static final String RESOURCES = "resources";
	private static final String RESOURCES_USERS = FileResourcePath.RESOURCES.toString() + File.separator + "users";
	private static final String IMAGE = "img";
	private final String filePath;

	private FileResourcePath() {
		this.filePath = FileResourcePath.RESOURCES;
	}

	private FileResourcePath(final String filePath) {
		this.filePath = filePath;
	}

	public static FileResourcePath createUploadPath() {
		return new FileResourcePath();
	}

	public static FileResourcePath createUploadImagePath(String user) {
		return new FileResourcePath(RESOURCES_USERS).addPath(user).addPath(IMAGE);
	}

	public FileResourcePath addPath(final String filePath) {
		return new FileResourcePath(this.filePath + File.separator + filePath);
	}

	public Path getPath() {
		return Paths.get(this.filePath);
	}
}
