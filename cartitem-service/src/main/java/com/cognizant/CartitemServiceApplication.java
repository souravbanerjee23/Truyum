package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication
public class CartitemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartitemServiceApplication.class, args);
	}
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Public - API").apiInfo(apiInfo()).select().paths(myPaths()).build();
	}
	private Predicate<String> myPaths(){
		return PathSelectors.regex("/cart/.*");
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Cart - API")
				.description("Cart Item API reference for frontend Developpers")
				.termsOfServiceUrl("http://localhost:8082/cart/menus").contact("mi@gmail.com")
				.license("Group - 2").licenseUrl("http://localhost:8082/cart/menus").version("1.0")
				.build();
	}


}
