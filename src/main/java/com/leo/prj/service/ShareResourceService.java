package com.leo.prj.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.service.img.ImageService;
import com.leo.prj.util.FileFilterUtil;
import com.leo.prj.util.FilePathUtil;

public abstract class ShareResourceService {
	public static final String LANDINGPAGE_EXTENSION = "ldp";
	private static final String HTML_EXTENSION = "html";

	private static final Logger logger = Logger.getLogger(ShareResourceService.class);

	@Autowired
	private ImageService imageService;

	public List<FileInfo> getAll() {
		return Stream.of(this.getDirectory().toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE))
				.map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	private Path createFilePath(String pageName) {
		return FilePathUtil.from(this.getDirectory()).add(pageName + CommonConstant.DOT + LANDINGPAGE_EXTENSION)
				.getPath();
	}

	private Path createHTMLPath(String pageName) {
		return FilePathUtil.from(this.getDirectory()).add(pageName + CommonConstant.DOT + HTML_EXTENSION).getPath();
	}

	public boolean save(EditorPageData data) {
		try {
			Files.write(this.createFilePath(data.getPageName()), data.getJsonContent().getBytes());
			Files.write(this.createHTMLPath(data.getPageName()), data.getHtmlContent().getBytes());
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	public abstract Path getDirectoryPath();

	public Optional<EditorPageData> load(String fileName) {
		final Path filePath = this.createFilePath(fileName);
		final Path fileHtmlPath = this.createHTMLPath(fileName);
		if (!Files.exists(filePath)) {
			return Optional.empty();
		}
		final EditorPageData editorPageData = new EditorPageData();
		editorPageData.setPageName(fileName);
		try {
			editorPageData.setJsonContent(Arrays.toString(Files.readAllBytes(filePath)));
			editorPageData.setHtmlContent(Arrays.toString(Files.readAllBytes(fileHtmlPath)));
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return Optional.of(editorPageData);
		}
		return Optional.of(editorPageData);
	}

	private FileInfo toFileInfo(File file) {
		final FileInfo fileInfo = new FileInfo(file);
		fileInfo.setThumbnail(this.createThumbnailUrl(file.getName()));
		return fileInfo;
	}

	public abstract String getThumbnailUrl();

	private String createUrl(String fileName) {
		final String url = this.getThumbnailUrl().replace("{fileName:.+}", fileName);
		return url;
	}

	private String createThumbnailUrl(String fileName) {
		final String thumbnailName = this.imageService.getThumbnailName(fileName);
		return this.createUrl(thumbnailName);
	}

	public Path getFilePath(String fileName) {
		return FilePathUtil.from(this.getDirectory()).add(fileName).getPath();
	}

	private Path getDirectory() {
		final Path path = this.getDirectoryPath();
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (final Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
		return path;
	}

	private boolean deleteFile(String fileName) {
		try {
			Files.deleteIfExists(this.createFilePath(fileName));
			Files.deleteIfExists(this.createHTMLPath(fileName));
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	public int delete(List<String> fileNames) {
		int deletedFile = 0;
		for (final String fileName : fileNames) {
			if (this.deleteFile(fileName)) {
				deletedFile++;
			}
		}
		return deletedFile;
	}
}
