package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.CustomerEntity;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerValidation {
    private CustomerValidation() {
    }
    public static boolean isInvalidCustomerRequestDto(CustomerRequestDto customerRequestDto) {
        /* check all required fields are not empty*/
        return customerRequestDto.customerDto().firstname().isBlank()
                || customerRequestDto.customerDto().lastname().isBlank()
                || customerRequestDto.customerDto().email().isBlank()
                || customerRequestDto.addressDto().streetNum() < 1
                || customerRequestDto.addressDto().streetName().isBlank()
                || customerRequestDto.addressDto().poBox() < 10000 //5 digits for postal code
                || customerRequestDto.addressDto().city().isBlank()
                || customerRequestDto.addressDto().city().length() < 3// minimum length of city name
                || customerRequestDto.addressDto().country().isBlank()
                || customerRequestDto.addressDto().birthCountry().isBlank();

    }
    public static boolean isInvalidCountry(String country){
        /* check entered country is valid*/
        List<String> worldCountries = WorldCountriesLoader.loadWorldCountries();
        return !worldCountries.contains(country);
    }

    public static boolean isInvalidBirthCountry(String birthCountry){
        /* check entered country is valid*/
        List<String> worldCountries = WorldCountriesLoader.loadWorldCountries();
        return !worldCountries.contains(birthCountry);
    }

    public static boolean isInvalidEmail(String email){
        /* check email is valid using simple regex*/
        final String regex="^(.+)@(\\S+)$";
        return ! Pattern.compile(regex)
                .matcher(email)
                .matches();
    }

    public static boolean emailAssigned(String email, List<String> emails){
        return emails.contains(email);
    }

    public static boolean customerExists(CustomerEntity customerEntity){
        return customerEntity!=null;
    }
}
