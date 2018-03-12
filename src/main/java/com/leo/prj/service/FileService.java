package com.leo.prj.service;

import java.util.List;

import com.leo.prj.bean.FileInfo;

public abstract class FileService {
	public abstract List<? extends FileInfo> getFiles(String user);
}
