package com.leo.prj.bean;

import java.util.Date;
import java.util.Optional;

public class File {
	private String fileName;
	private String fileType;
	private String tooltip;
	private Date createdDate;
	private Optional<String> url;

	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return this.fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getTooltip() {
		return this.tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public Date getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Optional<String> getUrl() {
		return this.url;
	}
	public void setUrl(Optional<String> url) {
		this.url = url;
	}
}
