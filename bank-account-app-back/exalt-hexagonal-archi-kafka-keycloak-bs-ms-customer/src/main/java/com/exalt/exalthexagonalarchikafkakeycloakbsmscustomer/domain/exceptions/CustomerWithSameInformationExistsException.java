package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.exceptions;

public class CustomerWithSameInformationExistsException extends RuntimeException{
    public CustomerWithSameInformationExistsException(String message) {
        super(message);
    }
}
