package com.leo.prj.bean;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.leo.prj.constant.CommonConstant;

public class FileInfo {
	private String fileName;
	private String fileFullName;
	private String fileType;
	private Date createdDate;
	private String url;
	private String thumbnail;

	private static final Logger logger = Logger.getLogger(FileInfo.class);

	public FileInfo(File file) {
		this.fileName = FilenameUtils.removeExtension(file.getName());
		this.fileFullName = file.getName();
		this.url = CommonConstant.EMPTY;
		this.thumbnail = CommonConstant.EMPTY;
		try {
			this.fileType = Files.probeContentType(file.toPath());
			final BasicFileAttributes readAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			this.createdDate = new Date(readAttributes.creationTime().toMillis());
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public String getFileFullName() {
		return this.fileFullName;
	}

	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}

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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
