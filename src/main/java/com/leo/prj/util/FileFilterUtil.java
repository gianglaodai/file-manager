package com.leo.prj.util;

import java.io.FileFilter;

import org.apache.commons.io.FilenameUtils;

import com.leo.prj.service.page.PageService;

public interface FileFilterUtil {
	public static final FileFilter IS_LANDING_PAGE = file -> FilenameUtils.getExtension(file.getName())
			.equals(PageService.LANDINGPAGE_EXTENSION);
}
