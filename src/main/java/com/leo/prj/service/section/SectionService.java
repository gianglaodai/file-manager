package com.leo.prj.service.section;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ResourceService;
import com.leo.prj.util.FilePathUtil;

@Service
public class SectionService extends ResourceService {
	private static final String SECTION_DIRECTORY = "section";

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.createSharePath().add(SECTION_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.SECTION_THUMBNAIL;
	}

}
