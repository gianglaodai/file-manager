package com.leo.prj.service.template;

import java.nio.file.Path;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leo.prj.bean.EditorPageData;
import com.leo.prj.constant.CommonConstant;
import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ResourceService;
import com.leo.prj.util.FilePathUtil;

@Service
public class TemplateService extends ResourceService {
	private static final String TEMPLATE_DIRECTORY = "template";

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.createSharePath().add(TemplateService.TEMPLATE_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.TEMPLATE_THUMBNAIL;
	}

	public String preview(String fileName, int catalog) {
		final Optional<EditorPageData> file = this.loadFromCatalog(catalog, fileName);
		if (file.isPresent()) {
			return file.get().getHtmlContent();
		}
		return CommonConstant.EMPTY;
	}

}
