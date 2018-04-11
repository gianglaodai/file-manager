package com.leo.prj.constant;

import java.nio.charset.Charset;

public interface CommonConstant {
	public static final String EMPTY = "";
	public static final String REQUEST_PATH_SEPARATOR = "/";
	public static final String THUMBNAIL = "thumbnail";
	public static final String DOT = ".";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final Charset CHARSET_UTF8 = Charset.forName(DEFAULT_CHARSET);

	public static interface URLConstant {
		public static final String RESOUCE_PATH_IMG = "img";
		public static final String ACCEPT_ORIGIN = "http://localhost:8080";
	}

}
