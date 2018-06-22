package com.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.water.dao")
public class WaterCmsApplication {

	public static void main(String[] args) {

		    SpringApplication.run(WaterCmsApplication.class, args);
	}
}
