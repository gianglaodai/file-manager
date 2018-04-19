package com.leo.prj.service.img;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.service.FileService;
import com.leo.prj.service.UserService;
import com.leo.prj.util.FilePathUtil;

@Service
public class ImageService implements FileService {
	public static final String IMG_DIRECTORY = "img";

	private static final Logger logger = Logger.getLogger(ImageService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ImageUploadService imageUploadService;

	@Override
	public List<File> getFiles() {
		final List<File> images = new ArrayList<>();
		final File imageDirectory = this.getUploadDirectory().toFile();
		if (!imageDirectory.exists()) {
			imageDirectory.mkdirs();
			return images;
		}
		return Stream.of(imageDirectory.listFiles(file -> file.isFile() && !this.isThumbnail(file)))
				.collect(Collectors.toList());
	}

	private boolean isThumbnail(File file) {
		return FilenameUtils.getExtension(FilenameUtils.removeExtension(file.getName()))
				.equals(CommonConstant.THUMBNAIL);
	}

	public final String getThumbnailName(String fileName) {
		return FilenameUtils.removeExtension(fileName) + CommonConstant.DOT + CommonConstant.THUMBNAIL
				+ CommonConstant.DOT + FilenameUtils.getExtension(fileName);
	}

	@Override
	public int deleteFiles(List<String> fileNames) {
		int deletedFile = 0;
		for (final String fileName : fileNames) {
			if (this.deleteFile(fileName)) {
				deletedFile++;
			}
		}
		return deletedFile;
	}

	public boolean deleteFile(String fileName) {
		try {
			Files.move(this.createFileUploadPath(fileName), this.createFileDeletePath(fileName),
					StandardCopyOption.REPLACE_EXISTING);
			final String thumbnailName = this.getThumbnailName(fileName);
			Files.move(this.createFileUploadPath(thumbnailName), this.createFileDeletePath(thumbnailName),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public UploadFilesResult upload(List<MultipartFile> uploadFiles) {
		final UploadFilesResult result = this.imageUploadService.uploadImages(uploadFiles,
				this.getUploadDirectory().toString());

		return result;
	}

	@Override
	public UploadFileStatus upload(MultipartFile uploadFile) {
		return this.imageUploadService.uploadImage(uploadFile, this.getUploadDirectory().toString());
	}

	public Path createFileUploadPath(String fileName) {
		return FilePathUtil.from(this.getUploadDirectory()).add(fileName).getPath();
	}

	public Path createFileUploadPath(String user, String fileName) {
		return FilePathUtil.from(this.getUploadDirectory(user)).add(fileName).getPath();
	}

	public Path createFileDeletePath(String fileName) {
		return FilePathUtil.from(this.getRecycleDirectory()).add(fileName).getPath();
	}

	public Path getUploadDirectory() {
		return FilePathUtil.from(this.userService.getCurrentUserDirectory()).add(IMG_DIRECTORY).getPath();
	}

	public Path getUploadDirectory(String user) {
		return FilePathUtil.from(this.userService.getCurrentUserDirectory(user)).add(IMG_DIRECTORY).getPath();
	}

	public Path getRecycleDirectory() {
		final Path recyclePath = FilePathUtil.from(this.getUploadDirectory()).add(FilePathUtil.RECYCLE).getPath();
		if (!Files.exists(recyclePath)) {
			recyclePath.toFile().mkdirs();
		}
		return recyclePath;
	}

}
