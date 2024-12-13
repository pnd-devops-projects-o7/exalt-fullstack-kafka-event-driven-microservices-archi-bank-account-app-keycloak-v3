package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayApplicationWeb {
    private static final Logger logger = LoggerFactory.getLogger(GatewayApplicationWeb.class);
    @GetMapping(value = "/token")
    public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient){
        logger.info("getting jwt token from authenticated client");
        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
    }
}
