package com.leo.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.leo.prj.controller", "com.leo.prj.service", "com.leo.prj.bean",
		"com.leo.prj.config" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
