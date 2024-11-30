package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto;


import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.State;

import java.time.Instant;
import java.util.UUID;

public record CustomerDto (UUID customerId, String firstname, String lastname, String email, State state, Instant createdAt){}
