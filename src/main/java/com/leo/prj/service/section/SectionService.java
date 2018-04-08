package com.leo.prj.service.section;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ShareResourceService;

@Service
public class SectionService extends ShareResourceService {
	private static final String SECTION_DIRECTORY = "section";

	@Override
	public String getDirectoryPath() {
		return SECTION_DIRECTORY;
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.SECTION_THUMBNAIL;
	}

}
