package com.leo.prj.enumeration;

public enum Popup implements CatalogStructure {
	ALL(0, "Tất cả", 0), REGISTER(1, "Đăng ký", 1), GREETING(2, "Lời chào", 2), CONTENT(3, "Nội dung", 3), THANK(4,
			"Cảm ơn", 4);

	private final int value;
	private final String label;
	private final int order;

	private Popup(int value, String label, int order) {
		this.value = value;
		this.label = label;
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
