FROM openjdk:21-ea-4-oracle AS build
WORKDIR /builder
ARG GATEWAY_PROXY_APPLICATION_NAME
ARG APPLICATION_TAG
ARG GATEWAY_PROXY_SERVER_PORT
ARG ROUTE1_ID
ARG ROUTE1_URI
ARG ROUTE1_PREDICATE
ARG ROUTE2_ID
ARG ROUTE2_URI
ARG ROUTE2_PREDICATE
ARG ROUTE3_ID
ARG ROUTE3_URI
ARG ROUTE3_PREDICATE
ARG ROUTE4_ID
ARG ROUTE4_URI
ARG ROUTE4_PREDICATE
ARG SERVICE_REGISTRY_APPLICATION_NAME
ARG SERVICE_REGISTRY_SERVER_PORT
ARG JAR_FILE=target/*.jar

ENV GATEWAY_PROXY_APPLICATION_NAME=${GATEWAY_PROXY_APPLICATION_NAME}
ENV APPLICATION_TAG=${APPLICATION_TAG}
ENV GATEWAY_PROXY_SERVER_PORT=${GATEWAY_PROXY_SERVER_PORT}
ENV ROUTE1_ID=${ROUTE1_ID}
ENV ROUTE1_URI=${ROUTE1_URI}
ENV ROUTE1_PREDICATE=${ROUTE1_PREDICATE}
ENV ROUTE2_ID=${ROUTE2_ID}
ENV ROUTE2_URI=${ROUTE2_URI}
ENV ROUTE2_PREDICATE=${ROUTE2_PREDICATE}
ENV ROUTE3_ID=${ROUTE3_ID}
ENV ROUTE3_URI=${ROUTE3_URI}
ENV ROUTE3_PREDICATE=${ROUTE3_PREDICATE}
ENV ROUTE4_ID=${ROUTE4_ID}
ENV ROUTE4_URI=${ROUTE4_URI}
ENV ROUTE4_PREDICATE=${ROUTE4_PREDICATE}
ENV SERVICE_REGISTRY_APPLICATION_NAME: ${SERVICE_REGISTRY_APPLICATION_NAME}
ENV SERVICE_REGISTRY_SERVER_PORT=${SERVICE_REGISTRY_SERVER_PORT}

COPY ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
FROM openjdk:21-ea-4-oracle AS final
WORKDIR /application
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./
EXPOSE ${GATEWAY_PROXY_SERVER_PORT}

ENTRYPOINT ["java", "-jar", "application.jar"]