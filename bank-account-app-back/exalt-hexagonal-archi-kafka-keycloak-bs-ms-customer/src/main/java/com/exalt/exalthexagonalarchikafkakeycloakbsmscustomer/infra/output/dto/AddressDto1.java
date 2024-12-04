package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto;


import java.util.UUID;

public record AddressDto1(UUID addressId, int streetNum, String streetName, int postalCode, String city, String country, String birthCountry) {

}
