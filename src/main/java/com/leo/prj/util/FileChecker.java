package com.leo.prj.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.constant.CommonConstant;
import com.leo.prj.enumeration.MediaType;
import com.leo.prj.enumeration.MimeType;
import com.leo.prj.enumeration.UploadFileStatus;

public abstract class FileChecker {
	@Autowired
	private Tika tika;

	private static final Logger logger = Logger.getLogger(FileChecker.class);

	public UploadFileStatus checkUploadFile(final MultipartFile file, final String directory) {
		if (file == null) {
			return UploadFileStatus.INVALID;
		}
		Path filePath = Paths.get(directory + File.separator + file.getOriginalFilename());
		if (Files.exists(filePath)) {
			return UploadFileStatus.EXIST;
		}
		String mimeType = CommonConstant.EMPTY;
		try {
			mimeType = this.tika.detect(file.getBytes());
		} catch (Exception e) {
			FileChecker.logger.error(e.getMessage());
			return UploadFileStatus.INVALID;
		}

		if (!mimeType.equals(file.getContentType())) {
			return UploadFileStatus.INVALID;
		}

		return this.isForbidden(mimeType) ? UploadFileStatus.FORBIDDEN : UploadFileStatus.VALID;
	}

	private boolean isForbidden(final String mimeType) {
		MediaType mediaType = MediaType.parse(mimeType);
		for (MimeType acceptType : this.acceptTypes()) {
			if (acceptType == mediaType.getType()) {
				return false;
			}
		}
		return true;
	}

	protected abstract List<MimeType> acceptTypes();
}
