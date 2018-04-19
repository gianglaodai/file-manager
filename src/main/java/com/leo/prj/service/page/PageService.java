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
		return FilePathUtil.from(this.userService.getCurrentUserDirectory()).add(PAGE_DIRECTORY).getPath();
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

	public boolean publish(String pageName, String product) {
		this.movePublishFileToDefault(product);
		this.moveToPublishFoler(pageName, product);
		return true;
	}

	private void moveToPublishFoler(String pageName, String product) {
		try {
			Files.move(this.createFilePath(this.getLandingPageName(pageName), product).toFile(),
					this.createPublishFilePath(this.getLandingPageName(pageName), product).toFile());
			Files.move(this.createFilePath(this.getHTMLName(pageName), product).toFile(),
					this.createPublishFilePath(this.getHTMLName(pageName), product).toFile());
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void movePublishFileToDefault(String product) {
		final Path directory = this.getDirectory(product);
		Stream.of(this.getPublishFolder(product).toFile().listFiles(FileFilterUtil.IS_LANDING_PAGE_RESOURCE))
				.forEach(file -> {
					try {
						Files.move(file, FilePathUtil.from(directory).add(file.getName()).getPath().toFile());
					} catch (final IOException e) {
						logger.error(e.getMessage(), e);
					}
				});
	}

	private Path getPublishFolder(String product) {
		return FilePathUtil.from(this.getDirectory(product)).add(PUBLISH_DIRECTORY).getPath();
	}

	private Path createPublishFilePath(String pageName, String product) {
		return FilePathUtil.from(this.getPublishFolder(product)).add(pageName).getPath();
	}
}
