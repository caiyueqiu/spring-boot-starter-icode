package com.icode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderZkPayment8004Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudProviderZkPayment8004Application.class, args);
	}

}
