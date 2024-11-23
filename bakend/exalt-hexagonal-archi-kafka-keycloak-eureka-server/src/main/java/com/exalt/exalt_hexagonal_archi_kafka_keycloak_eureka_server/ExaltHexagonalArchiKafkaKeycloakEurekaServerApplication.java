package com.exalt.exalt_hexagonal_archi_kafka_keycloak_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExaltHexagonalArchiKafkaKeycloakEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaltHexagonalArchiKafkaKeycloakEurekaServerApplication.class, args);
	}

}
