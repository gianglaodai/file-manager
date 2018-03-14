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
public class ImageService extends FileService {
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
		return Stream
				.of(imageDirectory.listFiles(file -> file.isFile() && !FilenameUtils
						.getExtension(FilenameUtils.removeExtension(file.getName())).equals(CommonConstant.THUMBNAIL)))
				.parallel().map(file -> this.toImageInfo(user, file)).collect(Collectors.toList());
	}

	private final String createFilePath(String user, String fileName) {
		final StringBuilder filePath = new StringBuilder(this.configurationProperties.getIpAddress());
		filePath.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(CommonConstant.URLConstant.RESOUCE_PATH_IMG)
				.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(user)
				.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(fileName);
		return filePath.toString();
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
}
