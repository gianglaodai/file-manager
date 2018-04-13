package com.leo.prj.service.img;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.FileInfoService;
import com.leo.prj.service.UserService;

@Service
public class ImageInfoService implements FileInfoService<FileInfo> {

	private static final Logger logger = Logger.getLogger(ImageInfoService.class);

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserService userService;

	@Override
	public List<FileInfo> getAll() {
		return this.imageService.getFiles().stream().map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	@Override
	public FileInfo toFileInfo(File file) {
		final FileInfo imageInfo = new FileInfo(file);
		final String fileName = file.getName();
		imageInfo.setFileName(FilenameUtils.removeExtension(FilenameUtils.removeExtension(fileName)));
		try {
			imageInfo.setUrl(this.createUrl(fileName));
			imageInfo.setThumbnail(this.createThumbnailUrl(fileName));
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
		}
		return imageInfo;
	}

	private String createUrl(String fileName) {
		String url = ResourceController.IMAGE_URL.replace("{user}", this.userService.getCurrentUser());
		try {
			url = url.replace("{fileName:.+}", URLEncoder.encode(fileName, CommonConstant.DEFAULT_CHARSET));
		} catch (final UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return url;
	}

	private String createThumbnailUrl(String fileName) {
		final String thumbnailName = this.imageService.getThumbnailName(fileName);
		return this.createUrl(thumbnailName);
	}
}
