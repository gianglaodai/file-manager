package com.leo.prj.enumeration;

public enum Popup implements CatalogStructure {
	ALL(0, "Tất cả"),
	REGISTER(1, "Đăng ký"),
	GREETING(2, "Lời chào"),
	CONTENT(3, "Nội dung"),
	THANK(4, "Cảm ơn");

	private final int value;
	private final String label;

	private Popup(int value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getLabel() {
		return this.label;
	}
}
