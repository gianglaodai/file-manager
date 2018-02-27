package com.leo.prj.service;

import java.io.IOException;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.MediaType;
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

	public UploadFilesResult uploadFile(MultipartFile uploadFile) throws Exception {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		final MediaType mediaType = MediaType.parse(this.tika.detect(uploadFile.getBytes()));
		if(mediaType.is(MediaType.APPLE_MOBILE_CONFIG)) {
		}
		return uploadFilesResult;
	}

	public UploadFilesResult uploadFiles(List<MultipartFile> uploadFiles) {
		final UploadFilesResult uploadFilesResult = this.uploadFilesResult;
		return uploadFilesResult;
	}
}
