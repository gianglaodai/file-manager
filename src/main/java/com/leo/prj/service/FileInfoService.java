package com.leo.prj.service;

import java.io.File;
import java.util.List;

import com.leo.prj.bean.FileInfo;

public interface FileInfoService<F extends FileInfo> {
	public List<F> getFileInfos();

	public F toFileInfo(File file);
}
