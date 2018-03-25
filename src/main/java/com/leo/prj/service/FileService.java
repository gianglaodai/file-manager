package com.leo.prj.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.enumeration.UploadFileStatus;

public interface FileService {

	public List<File> getFiles();

	public int deleteFiles(List<String> fileName);

	public UploadFilesResult upload(List<MultipartFile> uploadFiles);

	public UploadFileStatus upload(MultipartFile uploadFile);
}
