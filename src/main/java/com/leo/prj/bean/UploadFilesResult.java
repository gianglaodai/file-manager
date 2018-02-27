package com.leo.prj.bean;

public class UploadFilesResult {
	private int uploadedFiles;
	private int invalidFiles;
	private int fobiddenFiles;

	public UploadFilesResult() {
		this.uploadedFiles = 0;
		this.invalidFiles = 0;
		this.fobiddenFiles = 0;
	}

	public int getUploadedFiles() {
		return uploadedFiles;
	}

	public void increaseUploadedFiles() {
		this.uploadedFiles++;
	}

	public int getInvalidFiles() {
		return invalidFiles;
	}

	public void increaseInvalidFiles() {
		this.invalidFiles++;
	}

	public int getFobiddenFiles() {
		return fobiddenFiles;
	}

	public void increaseFobiddenFiles() {
		this.fobiddenFiles++;
	}
}
