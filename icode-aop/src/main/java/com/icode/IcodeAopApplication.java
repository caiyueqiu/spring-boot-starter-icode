package com.icode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IcodeAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcodeAopApplication.class, args);
	}

}
