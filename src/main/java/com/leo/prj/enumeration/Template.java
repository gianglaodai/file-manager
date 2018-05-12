package com.leo.prj.enumeration;

public enum Template implements CatalogStructure {
	All(0, "Tất cả", 0), REALTY(1, "Bất động sản & Nghỉ dưỡng", 1), EDUCATION(2, "Giáo dục & Đào tạo", 2), EVENT(3,
			"Sự kiện & Hội thảo", 3), TECHNOLOGY(4, "Công nghệ, Game & App", 4), HEALTH(5, "Sức khỏe & Làm đẹp",
					5), TRAVEL(6, "Du lịch & Nghỉ dưỡng", 6), FURNITURE(7, "Nội thất & Nhà cửa", 7), FINANCE(8,
							"Tài chính & Bảo hiểm", 8), WEDDING(9, "Cưới & Nhiếp ảnh", 9), FOOD(10, "Thực phẩm",
									10), RESTAURANT(11, "Nhà hàng & Quán ăn", 11), FASHION(12, "Thời trang và phụ kiện",
											12), ENTERTAINMENT(13, "Giải trí & Nghệ thuật", 13), BABY(14, "Mẹ & bé",
													14), VEHICLE(15, "Ô tô & Xe máy", 15), PROMOTION(16,
															"Quà tặng, Khuyến mãi & Cảm ơn", 16), PORTFOLIO(17,
																	"Portfolio, Profile & CV",
																	17), THANK(18, "Cảm ơn", 18);

	private final int value;
	private final String label;
	private final int order;

	private Template(int value, String label, int order) {
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
