package com.leo.prj.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.ImageInfo;
import com.leo.prj.util.FileResourcePath;

@Service
public class ImageService extends FileService {

	@Override
	public List<ImageInfo> getFiles(String user) {
		final List<ImageInfo> imageInfos = new ArrayList<>();
		final FileResourcePath imageFolderPath = FileResourcePath.createUploadImagePath(user);
		final File imageDirectory = imageFolderPath.getPath().toFile();
		if (!imageDirectory.exists()) {
			imageDirectory.mkdirs();
			return imageInfos;
		}
		return Stream.of(imageDirectory.listFiles(file -> file.isFile()
				&& !FilenameUtils.getExtension(FilenameUtils.removeExtension(file.getName())).equals("thumbnail")))
				.parallel().map(file -> ImageService.toImageInfo(user, file)).collect(Collectors.toList());
	}

	private static final String createFilePath(String user, String fileName) {
		return "http://localhost:8080/img/" + user + "/" + fileName;
	}

	private static final ImageInfo toImageInfo(String user, File file) {
		final ImageInfo imageInfo = new ImageInfo(file);
		final String fileName = file.getName();
		imageInfo.setUrl(createFilePath(user, fileName));
		imageInfo.setThumbnail(createFilePath(user,
				FilenameUtils.removeExtension(fileName) + ".thumbnail." + FilenameUtils.getExtension(fileName)));
		return imageInfo;
	}
}
