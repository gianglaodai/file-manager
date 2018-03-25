package com.leo.prj.bean;

import org.springframework.stereotype.Component;

@Component
public class UploadFilesResult {
	private int uploadedFiles;
	private int invalidFiles;
	private int existFiles;

	public UploadFilesResult() {
		this.uploadedFiles = 0;
		this.invalidFiles = 0;
		this.existFiles = 0;
	}

	public int getExistFiles() {
		return this.existFiles;
	}

	public void increaseExistFiles() {
		this.existFiles++;
	}

	public int getUploadedFiles() {
		return this.uploadedFiles;
	}

	public void increaseUploadedFiles() {
		this.uploadedFiles++;
	}

	public int getInvalidFiles() {
		return this.invalidFiles;
	}

	public void increaseInvalidFiles() {
		this.invalidFiles++;
	}
}
