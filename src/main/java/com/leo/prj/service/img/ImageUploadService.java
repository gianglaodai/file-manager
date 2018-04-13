package com.leo.prj.service.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.service.FileCheckerService;
import com.leo.prj.service.UploadService;
import com.leo.prj.util.FilePathUtil;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
public class ImageUploadService {
	private static final Logger logger = Logger.getLogger(ImageUploadService.class);
	private static final int MAX_THUMNAIL_WIDTH = 230;
	private static final int MAX_THUMNAIL_HEIGH = 200;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private FileCheckerService imageCheckerService;

	@Autowired
	private ObjectFactory<UploadFilesResult> resultFactory;

	public UploadFileStatus uploadImage(final MultipartFile uploadFile, final String directoryPath) {
		final String fileName = this.createFileName(uploadFile);
		if (this.imageCheckerService.isExist(directoryPath, fileName)) {
			logger.info("The path [" + directoryPath + File.separator + fileName + "] is existed");
			return UploadFileStatus.EXIST;
		}
		if (!this.imageCheckerService.isValid(uploadFile, directoryPath, fileName)) {
			logger.info("The file: '" + uploadFile.getName() + "' is invalid");
			return UploadFileStatus.INVALID;
		}
		final UploadFileStatus uploadFileStatus = this.uploadService.uploadFile(uploadFile,
				Paths.get(directoryPath).toFile(), fileName) ? UploadFileStatus.VALID : UploadFileStatus.INVALID;
		this.createThumbnail(FilePathUtil.from(directoryPath).add(fileName).getPath().toFile());
		return uploadFileStatus;
	}

	public boolean createThumbnail(final MultipartFile uploadFile, final Path directoryPath) {
		try {
			this.uploadService.uploadFile(uploadFile, directoryPath.toFile(), uploadFile.getOriginalFilename());
			Files.delete(FilePathUtil.from(directoryPath).add(uploadFile.getOriginalFilename()).getPath());
		} catch (final IOException e) {
			throw new RuntimeException("Can't delete file: " + uploadFile.getOriginalFilename());
		}
		return true;
	}

	public void createThumbnail(File file) {
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

	private String createFileName(MultipartFile uploadFile) {
		final String fileNameWithExtension = uploadFile.getOriginalFilename();
		return FilenameUtils.removeExtension(fileNameWithExtension) + CommonConstant.DOT + new Date().getTime()
				+ CommonConstant.DOT + FilenameUtils.getExtension(fileNameWithExtension);
	}

	public UploadFilesResult uploadImages(final List<MultipartFile> uploadFiles, String directoryPath) {
		final UploadFilesResult result = this.createUploadFilesResult();
		uploadFiles.forEach(file -> {
			final UploadFileStatus status = this.uploadImage(file, directoryPath);
			switch (status) {
			case INVALID:
				result.increaseInvalidFiles();
				return;
			case EXIST:
				result.increaseExistFiles();
				return;
			default:
				result.increaseUploadedFiles();
				return;
			}
		});
		return result;
	}

	private UploadFilesResult createUploadFilesResult() {
		return this.resultFactory.getObject();
	}
}
