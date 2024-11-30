package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Builder
@Getter
@Setter
public class ErrorDto {
    private int code;
    private String status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd: hh:mm:ss")
    private Timestamp timestamp;
}
