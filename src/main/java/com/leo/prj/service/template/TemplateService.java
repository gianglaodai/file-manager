package com.leo.prj.service.template;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ShareResourceService;
import com.leo.prj.util.FilePathUtil;

@Service
public class TemplateService extends ShareResourceService {
	private static final String TEMPLATE_DIRECTORY = "template";

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.createSharePath().add(TemplateService.TEMPLATE_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.TEMPLATE_THUMBNAIL;
	}

}
