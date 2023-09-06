package com.platzi.market;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API for Inventory control",
				version = "1.0",
				description = "This is an API REST designed to allow control of an inventory and applicable to any supermarket"
		)
)
public class PlatziMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				PlatziMarketApplication.class, args);
	}

}
