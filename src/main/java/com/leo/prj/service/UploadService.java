package com.leo.prj.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;
import com.leo.prj.enumeration.UploadFileStatus;
import com.leo.prj.util.FileResourcePath;

@Service
public abstract class UploadService {
	private UploadFileStatus uploadFile(final MultipartFile uploadFile, final String filePath) {
		final UploadFilesResult uploadFilesResult = new UploadFilesResult();
		final UploadFileStatus uploadFileStatus = this.checkUploadFile(uploadFile,
				FileResourcePath.createUploadImagePath(filePath).getPath().toString());
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
				final Path path = Files.write(FileResourcePath.createUploadImagePath(filePath)
						.addPath(uploadFile.getOriginalFilename()).getPath(), uploadFile.getBytes());
				this.afterUploadFile(new File(path.toUri()));
			} catch (final Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			uploadFilesResult.increaseUploadedFiles();
			break;
		}
		return uploadFileStatus;
	}

	public abstract UploadFileStatus checkUploadFile(MultipartFile uploadFile, String filePath);

	public void afterUploadFile(File file) {
	};

	public UploadFilesResult uploadImages(final List<MultipartFile> uploadFiles, final String filePath) {
		final UploadFilesResult uploadFilesResult = new UploadFilesResult();
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
