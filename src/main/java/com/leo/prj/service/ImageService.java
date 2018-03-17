package com.leo.prj.service;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.ConfigurationProperties;
import com.leo.prj.bean.ImageInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.util.FileResourcePath;

@Service
public class ImageService extends AbstractFileService implements FileService {
	@Autowired
	private ConfigurationProperties configurationProperties;

	@Override
	public List<ImageInfo> getFiles(String user) {
		final List<ImageInfo> imageInfos = new ArrayList<>();
		final FileResourcePath imageFolderPath = FileResourcePath.createUploadImagePath(user);
		final File imageDirectory = imageFolderPath.getPath().toFile();
		if (!imageDirectory.exists()) {
			imageDirectory.mkdirs();
			return imageInfos;
		}
		return Stream.of(imageDirectory.listFiles(file -> file.isFile() && !this.isThumbnail(file)))
				.map(file -> this.toImageInfo(user, file)).collect(Collectors.toList());
	}

	private boolean isThumbnail(File file) {
		return FilenameUtils.getExtension(FilenameUtils.removeExtension(file.getName()))
				.equals(CommonConstant.THUMBNAIL);
	}

	private final String createImageThumnailPath(String user, String fileName) {
		return this.createFilePath(user, this.getImageThumnailName(fileName));
	}

	private final String getImageThumnailName(String fileName) {
		return FilenameUtils.removeExtension(fileName) + CommonConstant.DOT + CommonConstant.THUMBNAIL
				+ CommonConstant.DOT + FilenameUtils.getExtension(fileName);
	}

	private final ImageInfo toImageInfo(String user, File file) {
		final ImageInfo imageInfo = new ImageInfo(file);
		final String fileName = file.getName();
		imageInfo.setUrl(this.createFilePath(user, fileName));
		imageInfo.setThumbnail(this.createImageThumnailPath(user, fileName));
		return imageInfo;
	}

	@Override
	public int deleteFiles(List<String> fileNames, String user) {
		int deletedFile = 0;
		for (final String fileName : fileNames) {
			if (this.deleteFile(fileName, user)) {
				deletedFile++;
			}
		}
		return deletedFile;
	}

	public boolean deleteFile(String fileName, String user) {
		final Path imagePath = this.getFileResourcePath(user, fileName);
		final File image = new File(imagePath.toUri());
		final Path thumbnailPath = this.getFileResourcePath(user, this.getImageThumnailName(image.getName()));
		final File thumbnail = new File(thumbnailPath.toUri());
		try {
			if (image.exists()) {
				image.delete();
			}
			if (thumbnail.exists()) {
				thumbnail.delete();
			}
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	protected String getResourcePath() {
		return CommonConstant.URLConstant.RESOUCE_PATH_IMG;
	}

	@Override
	protected FileResourcePath getBasicFileResourcePath(String user) {
		return FileResourcePath.createUploadImagePath(user);
	}
}
