package com.exalt.exalthexagonalarchikafkakeycloakgatewayserviceproxy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd: hh:mm:ss")
    private Timestamp timestamp;
}
