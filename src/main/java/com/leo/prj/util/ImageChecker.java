package com.leo.prj.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.leo.prj.enumeration.MimeType;

@Component
public class ImageChecker extends FileChecker {
	@Override
	protected List<MimeType> acceptTypes() {
		final List<MimeType> acceptTypes = new ArrayList<>();
		acceptTypes.add(MimeType.IMAGE);
		return acceptTypes;
	}
}
