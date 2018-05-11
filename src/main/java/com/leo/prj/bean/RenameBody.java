package com.leo.prj.bean;

public class RenameBody {
	private String pageName;
	private boolean publish;
	private String newPageName;

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getNewPageName() {
		return newPageName;
	}

	public void setNewPageName(String newPageName) {
		this.newPageName = newPageName;
	}

}
