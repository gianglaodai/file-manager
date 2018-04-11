package com.leo.prj.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class UtilConfiguration {
	@Bean
	public Tika tika() {
		return new Tika();
	}
}
