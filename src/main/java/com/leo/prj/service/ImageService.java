package com.leo.prj.service;

import java.io.File;
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
	public boolean deleteFile(String fileName, String user) {
		final String imagePath = this.createFilePath(user, fileName);
		final File image = new File(imagePath);
		final String thumbnailPath = this.createImageThumnailPath(user, fileName);
		final File thumbnail = new File(thumbnailPath);
		try {
			image.deleteOnExit();
			thumbnail.deleteOnExit();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	protected String getResourcePath() {
		return CommonConstant.URLConstant.RESOUCE_PATH_IMG;
	}
}
