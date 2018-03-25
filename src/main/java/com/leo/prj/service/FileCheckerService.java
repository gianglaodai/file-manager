package com.leo.prj.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.constant.CommonConstant;
import com.leo.prj.enumeration.MediaType;
import com.leo.prj.enumeration.MimeType;

public abstract class FileCheckerService {
	@Autowired
	private Tika tika;

	private static final Logger logger = Logger.getLogger(FileCheckerService.class);

	public boolean isExist(String directory, String fileName) {
		return Files.exists(Paths.get(directory + File.pathSeparator));
	}

	public boolean isValid(final MultipartFile file, final String directory, String fileName) {
		if ((file == null) || !Files.isDirectory(Paths.get(directory))) {
			return false;
		}
		if (Files.exists(Paths.get(directory + File.separator + fileName))) {
			return false;
		}
		String mimeType = CommonConstant.EMPTY;
		try {
			mimeType = this.tika.detect(file.getBytes());
		} catch (final Exception e) {
			FileCheckerService.logger.error(e.getMessage());
			return false;
		}

		if (!mimeType.equals(file.getContentType())) {
			return false;
		}

		return !this.isForbidden(mimeType);
	}

	private boolean isForbidden(final String mimeType) {
		final MediaType mediaType = MediaType.parse(mimeType);
		for (final MimeType acceptType : this.acceptTypes()) {
			if (acceptType == mediaType.getType()) {
				return false;
			}
		}
		return true;
	}

	protected abstract List<MimeType> acceptTypes();
}
