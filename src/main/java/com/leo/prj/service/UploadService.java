package com.leo.prj.service;

import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leo.prj.bean.UploadFilesResult;

@Service
public class UploadService {
	private UploadFilesResult uploadFilesResult;
	private Tika tika;

	@Autowired
	public void setUploadFilesResult(UploadFilesResult uploadFilesResult) {
		this.uploadFilesResult = uploadFilesResult;
	}

	@Autowired
	public void setTika(Tika tika) {
		this.tika = tika;
	}

	public UploadFilesResult uploadFile(MultipartFile uploadFile) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;

		return uploadFilesResult;
	}

	public UploadFilesResult uploadFiles(List<MultipartFile> uploadFiles) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		return uploadFilesResult;
	}
}
