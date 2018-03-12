package com.leo.prj.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.util.FileChecker;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
public class UploadImageService extends UploadService {
	private static final Logger logger = Logger.getLogger(UploadImageService.class);
	private static final int MAX_THUMNAIL_WIDTH = 230;
	private static final int MAX_THUMNAIL_HEIGH = 200;

	@Autowired
	private FileChecker imageChecker;

	@Override
	public UploadFileStatus checkUploadFile(MultipartFile uploadFile, String filePath) {
		return this.imageChecker.checkUploadFile(uploadFile, filePath);
	}

	@Override
	public void afterUploadFile(File file) {
		try {
			final BufferedImage bufferedImage = ImageIO.read(file);
			Thumbnails.of(file)
			.size(Math.min(MAX_THUMNAIL_WIDTH, bufferedImage.getWidth()),
					Math.min(MAX_THUMNAIL_HEIGH, bufferedImage.getHeight()))
			.useExifOrientation(true).useOriginalFormat().toFiles(Rename.SUFFIX_DOT_THUMBNAIL);
		} catch (final Exception e) {
			logger.error("Error happen when create thumnail", e);
			throw new RuntimeException(e);
		}
	}
}
