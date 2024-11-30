package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.exceptionhandler;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {
   @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> handlerBusinessExceptions(RuntimeException runtimeException){
        final int badRequestCode=400;
        final String badRequestStatus = HttpStatus.BAD_REQUEST.getReasonPhrase();
        final Timestamp createdAt = Timestamp.from(Instant.now());
       ErrorDto errorDto = ErrorDto.builder()
               .timestamp(createdAt)
               .build();

        switch (runtimeException) {
            case InvalidInputFieldsException businessException ->{
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case InputCountryNotExistsException businessException -> {
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case InputBirthCountryNotExistsException businessException ->{
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case EmailProvidedAlreadyAssignedException businessException ->{
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case CustomerWithSameInformationExistsException businessException->{
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case CustomerNotFoundException businessException -> {
                errorDto.setCode(404);
                errorDto.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
            }
            case CustomerAlreadyArchivedException businessException ->{
                errorDto.setCode(badRequestCode);
                errorDto.setStatus(badRequestStatus);
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorDto);
            }
            case CustomerStateArchivedException businessException -> {
                errorDto.setCode(403);
                errorDto.setStatus(HttpStatus.FORBIDDEN.getReasonPhrase());
                errorDto.setMessage(businessException.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
            }
            default -> {
                errorDto.setCode(500);
                errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                errorDto.setMessage(runtimeException.getMessage());
                return ResponseEntity.internalServerError().body(errorDto);
            }
        }
    }
}
