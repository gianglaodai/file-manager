package com.leo.prj.service.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.bean.FileInfo;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.service.UserService;
import com.leo.prj.util.FilePathUtil;

@Service
public class PageService {
	private static final String LANDINGPAGE_EXTENSION = "ldp";

	private static final String PAGE_DIRECTORY = "page";

	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(PageService.class);

	public boolean savePage(EditorPageData data) {
		final Path filePath = this.createFilePath(data.getPageName());
		try (OutputStream fos = new FileOutputStream(filePath.toFile());
				ObjectOutput oos = new ObjectOutputStream(fos);) {
			oos.writeObject(data.getJsonContent());
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	public List<FileInfo> getPages() {
		final Path saveDirectory = this.getSaveDirectory();
		final File directory = saveDirectory.toFile();
		return Stream
				.of(directory
						.listFiles(file -> FilenameUtils.getExtension(file.getName()).equals(LANDINGPAGE_EXTENSION)))
				.map(file -> new FileInfo(file)).collect(Collectors.toList());
	}

	private Path getSaveDirectory() {
		final Path path = FilePathUtil.from(this.userService.getCurrentUserDirectory()).add(PAGE_DIRECTORY).getPath();
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (final Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return path;
	}

	public Optional<EditorPageData> loadPage(String pageName) {
		final Path filePath = this.createFilePath(pageName);
		if (!Files.exists(filePath)) {
			return Optional.empty();
		}
		final EditorPageData editorPageData = new EditorPageData();
		editorPageData.setPageName(pageName);
		try (final InputStream fis = new FileInputStream(filePath.toFile());
				ObjectInput ois = new ObjectInputStream(fis)) {
			editorPageData.setJsonContent((String) ois.readObject());
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return Optional.empty();
		}
		return Optional.of(editorPageData);
	}

	private Path createFilePath(String pageName) {
		return FilePathUtil.from(this.getSaveDirectory()).add(pageName + CommonConstant.DOT + LANDINGPAGE_EXTENSION)
				.getPath();
	}
}
