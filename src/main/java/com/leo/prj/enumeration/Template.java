package com.leo.prj.enumeration;

public enum Template implements CatalogStructure {
	All(0, "Tất cả"),
	REALTY(1, "Bất động sản & Nghỉ dưỡng"),
	EDUCATION(2, "Giáo dục & Đào tạo"),
	EVENT(3, "Sự kiện & Hội thảo"),
	TECHNOLOGY(4, "Công nghệ, Game & App"),
	HEALTH(5, "Sức khỏe & Làm đẹp"),
	TRAVEL(6, "Du lịch & Nghỉ dưỡng"),
	FURNITURE(7, "Nội thất & Nhà cửa"),
	FINANCE(8, "Tài chính & Bảo hiểm"),
	WEDDING(9, "Cưới & Nhiếp ảnh"),
	FOOD(10, "Thực phẩm"),
	RESTAURANT(11, "Nhà hàng & Quán ăn"),
	FASHION(12, "Thời trang và phụ kiện"),
	ENTERTAINMENT(13, "Giải trí & Nghệ thuật"),
	BABY(14, "Mẹ & bé"),
	VEHICLE(15, "Ô tô & Xe máy"),
	PROMOTION(16, "Quà tặng, Khuyến mãi & Cảm ơn"),
	PORTFOLIO(17, "Portfolio, Profile & CV"),
	THANK(18, "Cảm ơn");

	private final int value;
	private final String label;

	private Template(int value, String label) {
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
