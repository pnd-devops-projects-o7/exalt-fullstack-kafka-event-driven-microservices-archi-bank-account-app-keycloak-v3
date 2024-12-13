package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * here our spring cloud gateway acts as backend gateway client (oauth2 client) to redirect users requests
 * to Identify Provider (IDP). In our case, IDP is Keycloak
 * Also, our spring cloud gateway acts as resource server (oauth2 resource server) because it exposes a single endpoint to
 * return a JWT token. All requests are secured, that is why here we implement a security filter
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity){
        httpSecurity
                .authorizeExchange(auth-> auth.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2)->oauth2.opaqueToken(Customizer.withDefaults()));
        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return httpSecurity.build();
    }
}
