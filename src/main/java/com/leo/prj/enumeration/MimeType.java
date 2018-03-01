package com.leo.prj.enumeration;

public enum MimeType {
	TEXT("text"), APPLICATION("audio"), AUDIO("audio"), VIDEO("video"), IMAGE("image"), ANY("*");

	private final String value;

	private MimeType(final String value) {
		this.value = value;
	}

	public static MimeType of(final String mimeType) {
		for (MimeType type : MimeType.values()) {
			if (type.value.equalsIgnoreCase(mimeType)) {
				return type;
			}
		}
		return ANY;
	}

}
