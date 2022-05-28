package com.icode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@SpringBootApplication
public class IcodeTkMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcodeTkMapperApplication.class, args);
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		return new MapperScannerConfigurer();
	}
}
