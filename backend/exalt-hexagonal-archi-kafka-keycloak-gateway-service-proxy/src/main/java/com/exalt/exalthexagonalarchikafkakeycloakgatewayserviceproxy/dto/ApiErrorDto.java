package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Builder
@Setter
@Getter
public class ApiErrorDto {
    private int errorCode;
    private String errorType;
    private String message;
    private Timestamp timestamp;
}
