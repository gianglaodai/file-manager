package com.leo.prj.service.popup;

import org.springframework.stereotype.Service;

import com.leo.prj.controller.ResourceController;
import com.leo.prj.service.ShareResourceService;

@Service
public class PopupService extends ShareResourceService {
	private static final String POPUP_DIRECTORY = "popup";

	@Override
	public String getDirectoryPath() {
		return POPUP_DIRECTORY;
	}

	@Override
	public String getThumbnailUrl() {
		return ResourceController.POPUP_THUMBNAIL;
	}
}
