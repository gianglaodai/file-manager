package com.leo.prj.service;

import java.io.File;
import java.util.List;

import com.leo.prj.bean.FileInfo;

public interface FileInfoService<F extends FileInfo> {
	public List<F> getAll();

	public F toFileInfo(File file);
}
