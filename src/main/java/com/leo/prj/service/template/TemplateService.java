package com.leo.prj.service.template;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ShareResourceService;

@Service
public class TemplateService extends ShareResourceService {
	private static final String TEMPLATE_DIRECTORY = "template";

	@Override
	public String getDirectoryPath() {
		return TemplateService.TEMPLATE_DIRECTORY;
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.TEMPLATE_THUMBNAIL;
	}

}
