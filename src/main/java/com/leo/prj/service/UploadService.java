package com.leo.prj.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.util.FilePathUtil;
import com.leo.prj.util.StringUtil;

@Service
public class UploadService {
	private static final Logger logger = Logger.getLogger(UploadService.class);

	public boolean uploadFile(@NotNull MultipartFile uploadFile, @NotNull File directory, String fileName) {
		if (!directory.isDirectory()) {
			logger.error("The path [" + directory.getPath() + "] is not a valid directory path");
			return false;
		}
		if (!directory.exists()) {
			directory.mkdirs();
		}
		fileName = StringUtil.isBlank(fileName) ? uploadFile.getName() : fileName;
		final Path filePath = FilePathUtil.from(directory.getPath()).add(fileName).getPath();
		if (Files.exists(filePath)) {
			logger.error("The path [" + filePath.toString() + "] is already existed");
			return false;
		}
		try {
			Files.write(filePath, uploadFile.getBytes());
			return true;
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
}
