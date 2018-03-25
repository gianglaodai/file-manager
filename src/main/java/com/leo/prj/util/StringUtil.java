package com.leo.prj.util;

public class StringUtil {
	public static final boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}
	
	public static final boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
}
