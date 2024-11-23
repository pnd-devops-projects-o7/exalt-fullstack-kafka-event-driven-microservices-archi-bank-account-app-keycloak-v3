package com.exalt.exalt_hexagonal_archi_kafka_keycloak_gateway_service_proxy.fallback;

import com.exalt.exalt_hexagonal_archi_kafka_keycloak_gateway_service_proxy.dto.ApiErrorDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class RoutingFallback {
    @Bean
    public RouterFunction<ServerResponse> defineRouteFallback(){
        return route("fallback_route")
                .GET("/fallback_route", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(ApiErrorDto.builder()
                                .errorCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                                .errorType(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                                .message("Service unavailable, please try later")
                                .timestamp(Timestamp.from(Instant.now()))
                                .build()))
                .build();
    }
}
