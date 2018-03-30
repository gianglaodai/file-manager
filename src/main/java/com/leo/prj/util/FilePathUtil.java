package com.leo.prj.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathUtil {
	private static final String RESOURCES = "resources";
	private static final String USERS = "users";
	public static final String RECYCLE = "recycle";
	public static final String SHARE = "share";
	private final String filePath;

	private FilePathUtil() {
		this.filePath = FilePathUtil.RESOURCES;
	}

	private FilePathUtil(final String filePath) {
		this.filePath = filePath;
	}

	public static FilePathUtil from(Path path) {
		return from(path.toString());
	}

	public static FilePathUtil from(String path) {
		return new FilePathUtil(path);
	}

	public static FilePathUtil createUploadPath() {
		return new FilePathUtil();
	}

	public static FilePathUtil createSharePath() {
		return createUploadPath().add(SHARE);
	}

	public static FilePathUtil createUploadUserPath() {
		return createUploadPath().add(USERS);
	}

	public FilePathUtil add(final String path) {
		return new FilePathUtil(this.filePath + File.separator + path);
	}

	public Path getPath() {
		return Paths.get(this.filePath);
	}
}
