package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.events_kafka.CustomerEvent;

public interface CustomerProducerEvent {
    void createCustomerEvent(CustomerEvent customerEvent);
    void updateCustomerEvent(CustomerEvent customerEvent);
    void customerArchiveEvent(CustomerEvent customerEvent);
}
