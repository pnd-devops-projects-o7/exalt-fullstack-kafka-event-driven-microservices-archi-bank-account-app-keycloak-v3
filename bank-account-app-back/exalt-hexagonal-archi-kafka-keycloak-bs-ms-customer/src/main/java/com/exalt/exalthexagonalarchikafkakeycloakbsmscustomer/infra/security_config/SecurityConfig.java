package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.security_config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(t -> {
                    t.requestMatchers(HttpMethod.GET, "/api/customer/").permitAll();
                    t.anyRequest().authenticated();
                });

        httpSecurity.oauth2ResourceServer(t -> {
           /*this use default converter to convert Keycloak JWT
            instead of using JWT default converter of Spring Security we define a JWT converter for converting
            Keycloak roles into Spring security roles
             */
          //  t.jwt(Customizer.withDefaults());
            t.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtAuthConverter));
        });
        return httpSecurity.build();
    }

    // defining our own converter
    @SuppressWarnings("unchecked")
    @Component
    public static class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
        @Value("${jwt.oauth.converter.client-id}")
        private String clientId;
        @Value("${jwt.oauth.converter.principal-attribute}")
        private String principalAttribute;

        private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        @Override
        public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
            Collection<GrantedAuthority> roles = Stream.concat(
                    jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                    extractAuthorities(jwt).stream()).collect(Collectors.toSet());
            return new JwtAuthenticationToken(jwt,roles,getPrincipeClaimName(jwt));
        }

        private String getPrincipeClaimName(Jwt jwt) {
            String claimName = JwtClaimNames.SUB;
            if(principalAttribute!= null){
                claimName = principalAttribute;
            }
            return jwt.getClaim(claimName);
        }

        private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
            Map<String, Object> resourcesAccess;
            Map<String, Object> resource;
            Collection<String> resourcesRoles;
            if (jwt.getClaim("resource_access") == null) {
                return Set.of();
            }
            resourcesAccess = jwt.getClaim("resource_access");
            if (resourcesAccess.get(clientId) == null) {
                return Set.of();
            }
            resource = (Map<String, Object>) resourcesAccess.get(clientId);
            resourcesRoles = (Collection<String>) resource.get("roles");
            return resourcesRoles.stream().map(
                    role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());

        }
    }
}
