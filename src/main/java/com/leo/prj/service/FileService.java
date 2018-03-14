package com.leo.prj.service;

import java.util.List;

import com.leo.prj.bean.FileInfo;

public interface FileService {

	public List<? extends FileInfo> getFiles(String user);

	public boolean deleteFile(String fileName, String user);
}
