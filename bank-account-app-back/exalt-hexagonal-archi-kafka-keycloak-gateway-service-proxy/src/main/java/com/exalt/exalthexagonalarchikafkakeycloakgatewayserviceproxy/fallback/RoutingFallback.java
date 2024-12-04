package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.fallback;

import com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.dto.ApiErrorDto;
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
    private static final int FALLBACK_CODE =503;
    private static final HttpStatus FALLBACK_STATUS = HttpStatus.SERVICE_UNAVAILABLE;
    private static final String FALLBACK_ENDPOINT ="/fallback_route";
    private static final String FALLBACK_ROUTE ="fallback_route";
    private static final String FALLBACK_MESSAGE = "[fallback] Service unavailable, please try later";
    private static final Timestamp FALLBACK_TIMESTAMP = Timestamp.from(Instant.now());

    @Bean
    public RouterFunction<ServerResponse> defineGETRouteFallback(){
        return route(FALLBACK_ROUTE)
                .GET(FALLBACK_ENDPOINT, request -> ServerResponse.status(FALLBACK_STATUS)
                        .body(ApiErrorDto.builder()
                                .errorCode(FALLBACK_CODE)
                                .errorType(FALLBACK_STATUS.getReasonPhrase())
                                .message(FALLBACK_MESSAGE)
                                .timestamp(FALLBACK_TIMESTAMP)
                                .build()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> definePOSTRouteFallback(){
        return route(FALLBACK_ROUTE)
                .POST(FALLBACK_ENDPOINT, request -> ServerResponse.status(FALLBACK_STATUS)
                        .body(ApiErrorDto.builder()
                                .errorCode(FALLBACK_CODE)
                                .errorType(FALLBACK_STATUS.getReasonPhrase())
                                .message(FALLBACK_MESSAGE)
                                .timestamp(FALLBACK_TIMESTAMP)
                                .build()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> definePUTRouteFallback(){
        return route(FALLBACK_ROUTE)
                .PUT(FALLBACK_ENDPOINT, request -> ServerResponse.status(FALLBACK_STATUS)
                        .body(ApiErrorDto.builder()
                                .errorCode(FALLBACK_CODE)
                                .errorType(FALLBACK_STATUS.getReasonPhrase())
                                .message(FALLBACK_MESSAGE)
                                .timestamp(FALLBACK_TIMESTAMP)
                                .build()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> defineDELETERouteFallback(){
        return route(FALLBACK_ROUTE)
                .DELETE(FALLBACK_ENDPOINT, request -> ServerResponse.status(FALLBACK_STATUS)
                        .body(ApiErrorDto.builder()
                                .errorCode(FALLBACK_CODE)
                                .errorType(FALLBACK_STATUS.getReasonPhrase())
                                .message(FALLBACK_MESSAGE)
                                .timestamp(FALLBACK_TIMESTAMP)
                                .build()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> definePATCHRouteFallback(){
        return route(FALLBACK_ROUTE)
                .POST(FALLBACK_ENDPOINT, request -> ServerResponse.status(FALLBACK_STATUS)
                        .body(ApiErrorDto.builder()
                                .errorCode(FALLBACK_CODE)
                                .errorType(FALLBACK_STATUS.getReasonPhrase())
                                .message(FALLBACK_MESSAGE)
                                .timestamp(FALLBACK_TIMESTAMP)
                                .build()))
                .build();
    }
}
