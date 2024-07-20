package com.danske.taxmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class TaxManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxManagerApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		return new CorsConfig().corsFilter();
	}

}
