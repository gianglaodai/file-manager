package com.leo.prj.bean;

import java.io.File;

public class ShareFileInfo extends FileInfo {
	private int catalog;
	private String thumbnailUrl;

	public ShareFileInfo(File file) {
		super(file);
	}

	public int getCatalog() {
		return this.catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

}
