package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.repository;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<AddressEntity,UUID> {
}
