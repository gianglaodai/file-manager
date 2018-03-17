package com.leo.prj.service;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.leo.prj.bean.ConfigurationProperties;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.util.FileResourcePath;

public abstract class AbstractFileService {
	@Autowired
	private ConfigurationProperties configurationProperties;

	protected final String createFilePath(String user, String fileName) {
		final StringBuilder filePath = new StringBuilder();
		filePath.append(this.getResourcePath()).append(CommonConstant.REQUEST_PATH_SEPARATOR).append(user)
				.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(fileName);
		return filePath.toString();
	}

	protected final Path getFileResourcePath(String user, String fileName) {
		final FileResourcePath fileResourcePath = this.getBasicFileResourcePath(user);
		return fileResourcePath.addPath(fileName).getPath();
	}

	protected abstract FileResourcePath getBasicFileResourcePath(String user);

	protected abstract String getResourcePath();
}
