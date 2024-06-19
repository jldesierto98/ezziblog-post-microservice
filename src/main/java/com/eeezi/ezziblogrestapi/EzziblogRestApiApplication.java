package com.eeezi.ezziblogrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Eezi Blog MicroService",
				description = "Eezi Blog springboot REST APIs for dev environment",
				version = "v1.0",
				contact = @Contact(
						name = "Joshua L. Desierto",
						email = "desiertojl98@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.desiertojl.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Springboot EeziBlog App",
				url = "https://github.com/jldesierto98/ezziblog-post-microservice"
		)
)
public class EzziblogRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(EzziblogRestApiApplication.class, args);
	}

}
