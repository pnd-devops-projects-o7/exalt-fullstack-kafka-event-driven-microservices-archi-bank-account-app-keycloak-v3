package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto;


import java.util.UUID;

public record AddressDto(UUID addressId, int streetNum, String streetName, int poBox, String city, String country, String birthCountry) {

}
