package com.leo.prj.bean;

import com.leo.prj.constant.CommonConstant;

public class Catalog {
	private int value;
	private String label;

	public Catalog() {
		this(0, CommonConstant.EMPTY);
	}

	public Catalog(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
