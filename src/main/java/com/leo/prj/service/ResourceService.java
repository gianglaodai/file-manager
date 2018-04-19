package com.leo.prj.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.bean.ShareFileInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.util.FileFilterUtil;
import com.leo.prj.util.FilePathUtil;

public abstract class ResourceService {
	public static final String LANDINGPAGE_EXTENSION = "ldp";
	public static final String HTML_EXTENSION = "html";

	private static final Logger logger = Logger.getLogger(ResourceService.class);

	public List<FileInfo> getAll() {
		return Stream.of(this.getDirectory().toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE))
				.map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	public List<FileInfo> getAll(String product) {
		return Stream.of(this.getDirectory(product).toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE))
				.map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	public List<FileInfo> getAllByCatalog(int catalog) {
		if (catalog == 0) {
			final List<FileInfo> files = new ArrayList<>();
			Stream.of(this.getDirectory().toFile().listFiles(file -> file.isDirectory()))
					.forEach(file -> files.addAll(this.getAllByDirectory(file, Integer.valueOf(file.getName()))));
			return files;
		}
		return this.getAllByDirectory(
				FilePathUtil.from(this.getDirectory()).add(String.valueOf(catalog)).getPath().toFile(), catalog);
	}

	private List<FileInfo> getAllByDirectory(File directory, int catalog) {
		if (!directory.exists()) {
			directory.mkdirs();
			return Collections.emptyList();
		}
		return Stream.of(directory.listFiles(FileFilterUtil.IS_LANDING_PAGE))
				.map(file -> this.toShareFileInfo(file, catalog)).collect(Collectors.toList());
	}

	private Path createFilePath(String pageName) {
		return FilePathUtil.from(this.getDirectory()).add(this.getLandingPageName(pageName)).getPath();
	}

	protected Path createFilePath(String pageName, String product) {
		return FilePathUtil.from(this.getDirectory(product)).add(this.getLandingPageName(pageName)).getPath();
	}

	protected String getLandingPageName(String pageName) {
		return pageName + CommonConstant.DOT + LANDINGPAGE_EXTENSION;
	}

	private Path createFilePath(int catalog, String pageName) {
		final FilePathUtil directory = FilePathUtil.from(this.getDirectory()).add(String.valueOf(catalog));
		final Path directoryPath = directory.getPath();
		if (!Files.exists(directoryPath)) {
			try {
				Files.createDirectories(directoryPath);
			} catch (final IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return directory.add(this.getLandingPageName(pageName)).getPath();
	}

	protected String getHTMLName(String pageName) {
		return pageName + CommonConstant.DOT + HTML_EXTENSION;
	}

	private Path createHTMLPath(String pageName) {
		return FilePathUtil.from(this.getDirectory()).add(this.getHTMLName(pageName)).getPath();
	}

	private Path createHTMLPath(String pageName, String product) {
		return FilePathUtil.from(this.getDirectory(product)).add(this.getHTMLName(pageName)).getPath();
	}

	private Path createHTMLPath(int catalog, String pageName) {
		return FilePathUtil.from(this.getDirectory()).add(String.valueOf(catalog)).add(this.getHTMLName(pageName))
				.getPath();
	}

	public boolean save(EditorPageData data) {
		return this.save(this.createFilePath(data.getPageName()), this.createHTMLPath(data.getPageName()), data);
	}

	public boolean save(EditorPageData data, String product) {
		return this.save(this.createFilePath(data.getPageName(), product),
				this.createHTMLPath(data.getPageName(), product), data);
	}

	private boolean save(Path filePath, Path fileHTMLPath, EditorPageData data) {
		try {
			Files.write(filePath, data.getJsonContent().getBytes(Charset.forName(CommonConstant.DEFAULT_CHARSET)));
			Files.write(fileHTMLPath, data.getHtmlContent().getBytes(Charset.forName(CommonConstant.DEFAULT_CHARSET)));
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	public boolean saveToCatalog(int catalog, EditorPageData data) {
		return this.save(this.createFilePath(catalog, data.getPageName()),
				this.createHTMLPath(catalog, data.getPageName()), data);
	}

	public abstract Path getDirectoryPath();

	public Optional<EditorPageData> load(String fileName) {
		final Path filePath = this.createFilePath(fileName);
		final Path fileHtmlPath = this.createHTMLPath(fileName);
		return this.load(filePath, fileHtmlPath, fileName);
	}

	public Optional<EditorPageData> load(String fileName, String product) {
		final Path filePath = this.createFilePath(fileName, product);
		final Path fileHtmlPath = this.createHTMLPath(fileName, product);
		return this.load(filePath, fileHtmlPath, fileName);
	}

	public Optional<EditorPageData> loadFromCatalog(String fileName, int catalog) {
		final Path filePath = this.createFilePath(catalog, fileName);
		final Path fileHtmlPath = this.createHTMLPath(catalog, fileName);
		return this.load(filePath, fileHtmlPath, fileName);
	}

	private Optional<EditorPageData> load(Path filePath, Path fileHtmlPath, String fileName) {
		if (!Files.exists(filePath)) {
			return Optional.empty();
		}
		final EditorPageData editorPageData = new EditorPageData();
		editorPageData.setPageName(fileName);
		try {
			editorPageData.setJsonContent(Files.readAllLines(filePath, CommonConstant.CHARSET_UTF8).get(0));
			editorPageData.setHtmlContent(Files.readAllLines(fileHtmlPath, CommonConstant.CHARSET_UTF8).stream()
					.collect(Collectors.joining()));
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return Optional.of(editorPageData);
		}
		return Optional.of(editorPageData);
	}

	public Optional<EditorPageData> loadFromCatalog(int catalog, String fileName) {
		final Path filePath = this.createFilePath(catalog, fileName);
		final Path fileHtmlPath = this.createHTMLPath(catalog, fileName);
		return this.load(filePath, fileHtmlPath, fileName);
	}

	private FileInfo toFileInfo(File file) {
		final FileInfo fileInfo = new FileInfo(file);
		fileInfo.setThumbnail(this.createThumbnailUrl(file.getName()));
		return fileInfo;
	}

	private FileInfo toShareFileInfo(File file, int catalog) {
		final ShareFileInfo fileInfo = new ShareFileInfo(file);
		fileInfo.setThumbnail(this.createThumbnailUrl(file.getName()));
		fileInfo.setCatalog(catalog);
		fileInfo.setThumbnailUrl(this.createThumbnailUrl(file.getName(), catalog));
		return fileInfo;
	}

	public abstract String getThumbnailUrl();

	private String createUrl(String fileName) {
		final String url = this.getThumbnailUrl().replace("{fileName:.+}", fileName);
		return url;
	}

	private String createUrl(String fileName, int catalog) {
		String url = this.getThumbnailUrl().replace("{fileName:.+}", fileName);
		url = url.replace("{catalog}", String.valueOf(catalog));
		return url;
	}

	private String createThumbnailUrl(String fileName) {
		final String thumbnailName = FilenameUtils.removeExtension(fileName) + CommonConstant.DOT
				+ CommonConstant.THUMBNAIL + CommonConstant.DOT + FilenameUtils.getExtension(".jpg");
		return this.createUrl(thumbnailName);
	}

	private String createThumbnailUrl(String fileName, int catalog) {
		final String thumbnailName = FilenameUtils.removeExtension(fileName) + CommonConstant.DOT
				+ CommonConstant.THUMBNAIL + CommonConstant.DOT + FilenameUtils.getExtension(".jpg");
		return this.createUrl(thumbnailName, catalog);
	}

	public Path getFilePath(String fileName) {
		return FilePathUtil.from(this.getDirectory()).add(fileName).getPath();
	}

	public Path getFilePath(int catalog, String fileName) {
		final FilePathUtil directory = FilePathUtil.from(this.getDirectory()).add(String.valueOf(catalog));
		if (!Files.exists(directory.getPath())) {
			try {
				Files.createDirectories(directory.getPath());
			} catch (final IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return directory.add(fileName).getPath();
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

	protected Path getDirectory(String product) {
		final Path path = FilePathUtil.from(this.getDirectoryPath()).add(product).getPath();
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

	private boolean deleteFile(String fileName, String product) {
		try {
			Files.deleteIfExists(this.createFilePath(fileName, product));
			Files.deleteIfExists(this.createHTMLPath(fileName, product));
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

	public int delete(List<String> fileNames, String product) {
		int deletedFile = 0;
		for (final String fileName : fileNames) {
			if (this.deleteFile(fileName, product)) {
				deletedFile++;
			}
		}
		return deletedFile;
	}
}
