package com.leo.prj.service.page;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;
import com.leo.prj.bean.EditorPageData;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.service.ResourceService;
import com.leo.prj.service.UserService;
import com.leo.prj.util.FileFilterUtil;
import com.leo.prj.util.FilePathUtil;

@Service
public class PageService extends ResourceService {

	private static final String PAGE_DIRECTORY = "page";
	private static final String PUBLISH_DIRECTORY = "publish";

	private static final Logger logger = Logger.getLogger(PageService.class);

	@Autowired
	private UserService userService;

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.from(userService.getCurrentUserDirectory()).add(PAGE_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return CommonConstant.EMPTY;
	}

	public String preview(String pageName) {
		final Optional<EditorPageData> file = this.load(pageName);
		if (file.isPresent()) {
			return file.get().getHtmlContent();
		}
		return CommonConstant.EMPTY;
	}

	public String preview(String pageName, String product) {
		final Optional<EditorPageData> file = this.load(pageName, product);
		if (file.isPresent()) {
			return file.get().getHtmlContent();
		}
		return CommonConstant.EMPTY;
	}

	public String preview(String pageName, String product, boolean publish) {
		final Optional<EditorPageData> file = this.load(pageName, product, publish);
		if (file.isPresent()) {
			return file.get().getHtmlContent();
		}
		return CommonConstant.EMPTY;
	}

	public boolean publish(String pageName, String product) {
		movePublishFileToDefault(product);
		moveToPublishFoler(pageName, product);
		return true;
	}

	private void moveToPublishFoler(String pageName, String product) {
		try {
			Files.move(createFilePath(getLandingPageName(pageName), product).toFile(),
					createPublishFilePath(getLandingPageName(pageName), product).toFile());
			Files.move(createFilePath(getHTMLName(pageName), product).toFile(),
					createPublishFilePath(getHTMLName(pageName), product).toFile());
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void movePublishFileToDefault(String product) {
		final Path directory = getDirectory(product);
		Stream.of(getPublishFolder(product).toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE_RESOURCE))
				.forEach(file -> {
					try {
						Files.move(file, FilePathUtil.from(directory).add(file.getName()).getPath().toFile());
					} catch (final IOException e) {
						logger.error(e.getMessage(), e);
					}
				});
	}

	private Path getPublishFolder(String product) {
		return FilePathUtil.from(getDirectory(product)).add(PUBLISH_DIRECTORY).getPath();
	}

	private Path createPublishFilePath(String pageName, String product) {
		return FilePathUtil.from(getPublishFolder(product)).add(pageName).getPath();
	}

	public boolean rename(String pageName, String newPageName, String product, boolean publish) {
		try {
			if (publish) {
				Files.move(createPublishFilePath(getLandingPageName(pageName), product).toFile(),
						createPublishFilePath(getLandingPageName(newPageName), product).toFile());
				Files.move(createPublishFilePath(getHTMLName(pageName), product).toFile(),
						createPublishFilePath(getHTMLName(newPageName), product).toFile());
			} else {
				Files.move(createFilePath(getLandingPageName(pageName), product).toFile(),
						createFilePath(getLandingPageName(newPageName), product).toFile());
				Files.move(createFilePath(getHTMLName(pageName), product).toFile(),
						createFilePath(getHTMLName(newPageName), product).toFile());
			}
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}
}
