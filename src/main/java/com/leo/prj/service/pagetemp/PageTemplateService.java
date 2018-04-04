package com.leo.prj.service.pagetemp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.img.ImageService;
import com.leo.prj.util.FileFilterUtil;
import com.leo.prj.util.FilePathUtil;

@Service
public class PageTemplateService {
	private static final String TEMPLATE_DIRECTORY = "template";
	private static final Logger logger = Logger.getLogger(PageTemplateService.class);

	@Autowired
	private ImageService imageService;

	public List<FileInfo> getTemplates() {
		return Stream.of(this.getDirectory().toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE))
				.map(file -> this.toFileInfo(file)).collect(Collectors.toList());
	}

	public Optional<EditorPageData> loadTemplate(String templateName){
		Path templatePath = FilePathUtil.from(this.getDirectory()).add(TEMPLATE_DIRECTORY).getPath();
		if (!Files.exists(templatePath)) {
			return Optional.empty();
		}
		final EditorPageData editorPageData = new EditorPageData();
		editorPageData.setPageName(templateName);
		try (final InputStream fis = new FileInputStream(templatePath.toFile());
				ObjectInput ois = new ObjectInputStream(fis)) {
			editorPageData.setJsonContent((String) ois.readObject());
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return Optional.empty();
		}
		return Optional.of(editorPageData); 
	}

	private FileInfo toFileInfo(File file) {
		final FileInfo fileInfo = new FileInfo(file);
		fileInfo.setThumbnail(this.createThumbnailUrl(file.getName()));
		return fileInfo;
	}

	private String createUrl(String fileName) {
		final String url = ResourceController.TEMPLATE_THUMBNAIL.replace("{fileName:.+}", fileName);
		return url;
	}

	private String createThumbnailUrl(String fileName) {
		final String thumbnailName = this.imageService.getThumbnailName(fileName);
		return this.createUrl(thumbnailName);
	}

	public Path getFilePath(String fileName) {
		return FilePathUtil.from(this.getDirectory()).add(fileName).getPath();
	}

	public Path getDirectory() {
		final Path path = FilePathUtil.createSharePath().add(TEMPLATE_DIRECTORY).getPath();
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
}
