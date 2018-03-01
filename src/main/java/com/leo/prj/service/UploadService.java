package com.leo.prj.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.constant.FileResourcePath;
import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.util.FileChecker;

@Service
public class UploadService {
	@Autowired
	private UploadFilesResult uploadFilesResult;

	@Autowired
	private FilePathService filePathService;

	@Autowired
	private FileChecker imageChecker;

	public UploadFilesResult uploadFile(final MultipartFile uploadFile) throws Exception {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		UploadFileStatus uploadFileStatus = this.imageChecker.checkUploadFile(uploadFile,
				FileResourcePath.createUploadPath().getPath().toString());
		switch (uploadFileStatus) {
		case EXIST:
			uploadFilesResult.increaseExistFiles();
			break;
		case INVALID:
			uploadFilesResult.increaseInvalidFiles();
			break;
		case FORBIDDEN:
			uploadFilesResult.increaseFobiddenFiles();
			break;
		default:
			Files.write(Paths.get(this.filePathService.getFilePath()), uploadFile.getBytes());
			uploadFilesResult.increaseUploadedFiles();
			break;
		}
		return uploadFilesResult;
	}

	public UploadFilesResult uploadFiles(final List<MultipartFile> uploadFiles) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		return uploadFilesResult;
	}
}
