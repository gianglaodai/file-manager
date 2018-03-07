package com.leo.prj.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {
	@Bean
	public Tika tika() {
		return new Tika();
	}

}
