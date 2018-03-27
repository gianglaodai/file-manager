package com.leo.prj.bean;

import com.leo.prj.constant.CommonConstant;

public class EditorPageData {
	private String pageName;
	private String jsonContent;
	private String htmlContent;

	public EditorPageData() {
		this.pageName = CommonConstant.EMPTY;
		this.jsonContent = CommonConstant.EMPTY;
		this.htmlContent = CommonConstant.EMPTY;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getJsonContent() {
		return this.jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}

	public String getHtmlContent() {
		return this.htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

}
