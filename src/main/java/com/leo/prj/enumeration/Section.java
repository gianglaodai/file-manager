package com.leo.prj.enumeration;

public enum Section implements CatalogStructure {
	ALL(0, "Tất cả"),
	CHARACTERISTIC(1, "Đặc điểm nổi bật"),
	BENEFIT(2, "Lợi ích khách hàng"),
	DETAIL(3, "Nội dung chi tiết"),
	TEAM(4, "Đội nhóm"),
	FEEDBACK(5, "Ý kiến khách hàng"),
	CERTIFICATION(6, "Chứng nhận & Cam kết"),
	PRICE_LIST(7, "Bảng giá"),
	FORM(8, "Form đăng ký"),
	CALL_TO_ACTION(9, "Call-to-Action"),
	FAQ(10, "FAQ"),
	FOOTER(11, "Footer"),
	THANK(12, "Cảm ơn");

	private final int value;
	private final String label;

	private Section(int value, String label) {
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
