package com.leo.prj.service;

import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.util.FileChecker;
import com.leo.prj.util.FileResourcePath;

@Service
public class UploadService {
	@Autowired
	private UploadFilesResult uploadFilesResult;

	@Autowired
	private FileChecker imageChecker;

	private UploadFileStatus uploadFile(final MultipartFile uploadFile, String filePath) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		final UploadFileStatus uploadFileStatus = this.imageChecker.checkUploadFile(uploadFile,
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
			try {
				Files.write(FileResourcePath.createUploadPath().addPath(filePath).addPath(uploadFile.getOriginalFilename()).getPath(), uploadFile.getBytes());
			} catch (final Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			uploadFilesResult.increaseUploadedFiles();
			break;
		}
		return uploadFileStatus;
	}

	public UploadFilesResult uploadFiles(final List<MultipartFile> uploadFiles, String filePath) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		for (final MultipartFile uploadFile : uploadFiles) {
			final UploadFileStatus result = this.uploadFile(uploadFile, filePath);
			switch (result) {
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
				uploadFilesResult.increaseUploadedFiles();
				break;
			}
		}
		return uploadFilesResult;
	}

}
