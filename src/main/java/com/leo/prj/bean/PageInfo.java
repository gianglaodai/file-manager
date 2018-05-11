package com.leo.prj.bean;

import java.io.File;

public class PageInfo extends FileInfo {
	private boolean publish;

	public PageInfo(File file) {
		super(file);
		publish = false;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}
}
