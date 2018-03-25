package com.leo.prj.service.img;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.prj.enumeration.MimeType;
import com.leo.prj.service.FileCheckerService;

@Service
public class ImageCheckerService extends FileCheckerService {

	@Override
	protected List<MimeType> acceptTypes() {
		final List<MimeType> acceptTypes = new ArrayList<>();
		acceptTypes.add(MimeType.IMAGE);
		return acceptTypes;
	}

}
