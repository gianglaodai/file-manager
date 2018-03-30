package com.leo.prj.service.img;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.FileInfo;
import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.FileInfoService;
import com.leo.prj.service.UserService;

@Service
public class ImageInfoService implements FileInfoService<FileInfo> {

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserService userService;

	@Override
	public List<FileInfo> getFileInfos() {
		return this.imageService.getFiles().stream().map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	@Override
	public FileInfo toFileInfo(File file) {
		final FileInfo imageInfo = new FileInfo(file);
		final String fileName = file.getName();
		imageInfo.setFileName(FilenameUtils.removeExtension(FilenameUtils.removeExtension(fileName)));
		imageInfo.setUrl(this.createUrl(fileName));
		imageInfo.setThumbnail(this.createThumbnailUrl(fileName));
		return imageInfo;
	}

	private String createUrl(String fileName) {
		String url = ResourceController.IMAGE_URL.replace("{user}", this.userService.getCurrentUser());
		url = url.replace("{fileName:.+}", fileName);
		return url;
	}

	private String createThumbnailUrl(String fileName) {
		final String thumbnailName = this.imageService.getThumbnailName(fileName);
		return this.createUrl(thumbnailName);
	}
}
