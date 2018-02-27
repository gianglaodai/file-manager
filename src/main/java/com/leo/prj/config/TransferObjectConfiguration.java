package com.leo.prj.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.leo.prj.bean.UploadFilesResult;

@Configuration
public class TransferObjectConfiguration {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public UploadFilesResult uploadFilesResult() {
		return new UploadFilesResult();
	}
}
