package com.leo.prj.bean;

import java.util.ArrayList;
import java.util.List;

public class UploadFilesResult {
	private int uploadedFiles;
	private int invalidFiles;
	private int fobiddenFiles;
	private int existFiles;
	private final List<FileInfo> files;

	public UploadFilesResult() {
		this.uploadedFiles = 0;
		this.invalidFiles = 0;
		this.fobiddenFiles = 0;
		this.existFiles = 0;
		this.files = new ArrayList();
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

	public int getFobiddenFiles() {
		return this.fobiddenFiles;
	}

	public void increaseFobiddenFiles() {
		this.fobiddenFiles++;
	}
}
