package com.leo.prj.bean;

import java.io.File;

public class ImageInfo extends FileInfo {
	private String thumbnail;

	public ImageInfo(File file) {
		super(file);
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
