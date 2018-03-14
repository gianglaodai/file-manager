package com.leo.prj.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.leo.prj.bean.ConfigurationProperties;
import com.leo.prj.constant.CommonConstant;

public abstract class AbstractFileService {
	@Autowired
	private ConfigurationProperties configurationProperties;

	protected final String createFilePath(String user, String fileName) {
		final StringBuilder filePath = new StringBuilder(this.configurationProperties.getIpAddress());
		filePath.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(getResourcePath())
				.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(user)
				.append(CommonConstant.REQUEST_PATH_SEPARATOR).append(fileName);
		return filePath.toString();
	}

	protected abstract String getResourcePath();
}
