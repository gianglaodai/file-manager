package com.leo.prj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leo.prj.bean.UploadFilesResult;

@Configuration
public class TransferObjectConfiguration {
	@Bean
	public UploadFilesResult uploadFilesResult() {
		return new UploadFilesResult();
	}

}
