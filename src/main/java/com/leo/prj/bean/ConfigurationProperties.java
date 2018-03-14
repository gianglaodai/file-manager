package com.leo.prj.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationProperties {
	@Value("${server.port}")
	private String port;

	public String getPort() {
		return this.port;
	}

	public String getIpAddress() {
		return "http://localhost:" + this.port;
	}
}
