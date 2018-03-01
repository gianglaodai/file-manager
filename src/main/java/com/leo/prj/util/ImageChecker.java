package com.leo.prj.util;

import java.util.ArrayList;
import java.util.List;

import com.leo.prj.enumeration.MimeType;

public class ImageChecker extends FileChecker {
	@Override
	protected List<MimeType> acceptTypes() {
		List<MimeType> acceptTypes = new ArrayList<>();
		acceptTypes.add(MimeType.IMAGE);
		return acceptTypes;
	}
}
