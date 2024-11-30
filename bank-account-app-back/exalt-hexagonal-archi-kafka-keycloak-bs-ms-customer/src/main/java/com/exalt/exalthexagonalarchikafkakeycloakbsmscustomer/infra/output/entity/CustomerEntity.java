package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events.State;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@ToString
public class CustomerEntity{
    @Id
    private UUID customerId;
    private  String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private State state;
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}
