package com.leo.prj.service.page;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.prj.constant.CommonConstant;
import com.leo.prj.service.ShareResourceService;
import com.leo.prj.service.UserService;
import com.leo.prj.util.FilePathUtil;

@Service
public class PageService extends ShareResourceService {

	private static final String PAGE_DIRECTORY = "page";

	@Autowired
	private UserService userService;

	@Override
	public Path getDirectoryPath() {
		return FilePathUtil.from(this.userService.getCurrentUserDirectory()).add(PAGE_DIRECTORY).getPath();
	}

	@Override
	public String getThumbnailUrl() {
		return CommonConstant.EMPTY;
	}

}
