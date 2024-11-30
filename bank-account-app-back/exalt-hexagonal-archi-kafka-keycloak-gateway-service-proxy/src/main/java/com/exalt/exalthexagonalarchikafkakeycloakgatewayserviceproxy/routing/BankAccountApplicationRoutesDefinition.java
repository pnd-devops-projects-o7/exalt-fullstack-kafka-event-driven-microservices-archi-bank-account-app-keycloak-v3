package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.routing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class BankAccountApplicationRoutesDefinition {
    @Value("${spring.cloud.gateway.route1.id}")
    private String bankAccountRouteId;
    @Value("${spring.cloud.gateway.route1.uri}")
    private String bankAccountRouteUri;
    @Value("${spring.cloud.gateway.route1.predicate}")
    private String bankAccountRoutePredicate;

    @Value("${spring.cloud.gateway.route2.id}")
    private String customerRouteId;
    @Value("${spring.cloud.gateway.route2.uri}")
    private String customerRouteUri;
    @Value("${spring.cloud.gateway.route2.predicate}")
    private String customerRoutePredicate;

    @Value("${spring.cloud.gateway.route3.id}")
    private String operationRouteId;
    @Value("${spring.cloud.gateway.route3.uri}")
    private String operationRouteUri;
    @Value("${spring.cloud.gateway.route3.predicate}")
    private String operationRoutePredicate;

    @Value("${spring.cloud.gateway.route4.id}")
    private String notificationRouteId;
    @Value("${spring.cloud.gateway.route4.uri}")
    private String notificationRouteUri;
    @Value("${spring.cloud.gateway.route4.predicate}")
    private String notificationRoutePredicate;
    private static final String FALLBACK_ROUTE = "forward:/fallback_route";

    @Bean
    public RouterFunction<ServerResponse> defineBankAccountApiRoutes() {
        return GatewayRouterFunctions
                .route(bankAccountRouteId)
                .route(RequestPredicates.path(bankAccountRoutePredicate),
                        HandlerFunctions.http(bankAccountRouteUri))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("bank-account-circuit-breaker", URI.create(FALLBACK_ROUTE)))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> defineCustomerApiRoutes(){
        return GatewayRouterFunctions
                .route(customerRouteId)
                .route(RequestPredicates.path(customerRoutePredicate),
                        HandlerFunctions.http(customerRouteUri))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("customer-circuit-breaker", URI.create(FALLBACK_ROUTE)))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> defineOperationApiRoutes() {
        return GatewayRouterFunctions.route(operationRouteId)
                .route(RequestPredicates.path(operationRoutePredicate),
                        HandlerFunctions.http(operationRouteUri))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("operation-circuit-breaker", URI.create(FALLBACK_ROUTE)))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> defineNotificationApiRoutes(){
        return GatewayRouterFunctions.route(notificationRouteId)
                .route(RequestPredicates.path(notificationRoutePredicate),
                        HandlerFunctions.http(notificationRouteUri))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("notification-circuit-breaker", URI.create(FALLBACK_ROUTE)))
                .build();
    }
}
