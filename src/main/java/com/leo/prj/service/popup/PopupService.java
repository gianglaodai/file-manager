package com.leo.prj.service.popup;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ShareResourceService;
import com.leo.prj.util.FilePathUtil;

@Service
public class PopupService extends ShareResourceService {
	private static final String POPUP_DIRECTORY = "popup";

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.createSharePath().add(POPUP_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.POPUP_THUMBNAIL;
	}
}
