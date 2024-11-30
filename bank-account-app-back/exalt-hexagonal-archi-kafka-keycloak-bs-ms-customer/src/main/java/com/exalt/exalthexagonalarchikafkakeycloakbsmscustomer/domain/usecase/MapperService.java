package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.Address;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.State;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.CustomerEntity;

import java.time.Instant;
import java.util.UUID;

public class MapperService {
    private MapperService() {
    }

    public static Customer map(CustomerRequestDto customerRequestDto) {
        return Customer.newBuilder()
                .setCustomerId(UUID.randomUUID())
                .setFirstname(customerRequestDto.customerDto().firstname())
                .setLastname(customerRequestDto.customerDto().lastname())
                .setEmail(customerRequestDto.customerDto().email())
                .setState(State.ACTIVE)
                .setCreatedAt(Instant.now())
                .setAddress(Address.newBuilder()
                        .setAddressId(UUID.randomUUID())
                        .setStreetNum(customerRequestDto.addressDto().streetNum())
                        .setStreetName(customerRequestDto.addressDto().streetName())
                        .setPoBox(customerRequestDto.addressDto().poBox())
                        .setCity(customerRequestDto.addressDto().city())
                        .setCountry(customerRequestDto.addressDto().country())
                        .setBirthCountry(customerRequestDto.addressDto().birthCountry())
                        .build())
                .build();
    }

    public static CustomerEntity map(Customer customer) {
        return CustomerEntity.builder()
                .customerId(customer.getCustomerId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .state(customer.getState())
                .createdAt(customer.getCreatedAt())
                .addressEntity(AddressEntity.builder()
                        .addressId(customer.getAddress().getAddressId())
                        .streetNum(customer.getAddress().getStreetNum())
                        .streetName(customer.getAddress().getStreetName())
                        .poBox(customer.getAddress().getPoBox())
                        .city(customer.getAddress().getCity())
                        .country(customer.getAddress().getCountry())
                        .birthCountry(customer.getAddress().getBirthCountry())
                        .build())
                .build();
    }

    public static CustomerResponseDto map(CustomerEntity customerEntity) {
        return new CustomerResponseDto(
                new CustomerDto(customerEntity.getCustomerId(),
                        customerEntity.getFirstname(), customerEntity.getLastname(),
                        customerEntity.getEmail(),
                        customerEntity.getState(),
                        customerEntity.getCreatedAt()),

                new AddressDto(customerEntity.getAddressEntity().getAddressId(),
                        customerEntity.getAddressEntity().getStreetNum(),
                        customerEntity.getAddressEntity().getStreetName(),
                        customerEntity.getAddressEntity().getPoBox(),
                        customerEntity.getAddressEntity().getCity(),
                        customerEntity.getAddressEntity().getCountry(),
                        customerEntity.getAddressEntity().getBirthCountry())
        );
    }

    public static CustomerEntity map(CustomerResponseDto customerResponseDto) {
        return CustomerEntity.builder()
                .customerId(customerResponseDto.customerDto().customerId())
                .firstname(customerResponseDto.customerDto().firstname())
                .lastname(customerResponseDto.customerDto().lastname())
                .email(customerResponseDto.customerDto().email())
                .state(customerResponseDto.customerDto().state())
                .createdAt(customerResponseDto.customerDto().createdAt())
                .addressEntity(AddressEntity.builder()
                        .addressId(customerResponseDto.addressDto().addressId())
                        .streetNum(customerResponseDto.addressDto().streetNum())
                        .streetName(customerResponseDto.addressDto().streetName())
                        .poBox(customerResponseDto.addressDto().poBox())
                        .city(customerResponseDto.addressDto().city())
                        .country(customerResponseDto.addressDto().country())
                        .birthCountry(customerResponseDto.addressDto().birthCountry())
                        .build())
                .build();
    }

    public static CustomerEvent map2(CustomerEntity customerEntity) {
        return CustomerEvent.newBuilder()
                .setCustomer(Customer.newBuilder()
                        .setCustomerId(customerEntity.getCustomerId())
                        .setFirstname(customerEntity.getFirstname())
                        .setLastname(customerEntity.getLastname())
                        .setEmail(customerEntity.getEmail())
                        .setState(customerEntity.getState())
                        .setCreatedAt(customerEntity.getCreatedAt())
                        .setAddress(Address.newBuilder()
                                .setAddressId(customerEntity.getAddressEntity().getAddressId())
                                .setStreetNum(customerEntity.getAddressEntity().getStreetNum())
                                .setStreetName(customerEntity.getAddressEntity().getStreetName())
                                .setPoBox(customerEntity.getAddressEntity().getPoBox())
                                .setCity(customerEntity.getAddressEntity().getCity())
                                .setCountry(customerEntity.getAddressEntity().getCountry())
                                .setBirthCountry(customerEntity.getAddressEntity().getBirthCountry())
                                .build())
                        .build())
                .setMessage("")
                .setStatus("")
                .build();
    }
}
