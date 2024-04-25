package com.example.manageaid;

import com.example.manageaid.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ManageAidApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageAidApplication.class, args);
	}

}
