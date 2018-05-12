package com.leo.prj.enumeration;

public enum Section implements CatalogStructure {
	ALL(0, "Tất cả", 0), INTRO(1, "Intro", 1), CHARACTERISTIC(2, "Đặc điểm nổi bật", 2), BENEFIT(3,
			"Lợi ích khách hàng", 3), DETAIL(4, "Nội dung chi tiết", 4), TEAM(5, "Đội nhóm", 5), FEEDBACK(6,
					"Ý kiến khách hàng", 6), CERTIFICATION(7, "Chứng nhận & Cam kết", 7), PRICE_LIST(8, "Bảng giá",
							8), FORM(9, "Form đăng ký", 9), CALL_TO_ACTION(10, "Call-to-Action",
									10), FAQ(11, "FAQ", 11), FOOTER(12, "Footer", 12), THANK(13, "Cảm ơn", 13);

	private final int value;
	private final String label;
	private final int order;

	private Section(int value, String label, int order) {
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
