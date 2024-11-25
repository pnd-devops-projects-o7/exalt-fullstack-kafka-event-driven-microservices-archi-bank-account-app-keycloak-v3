FROM openjdk:21-ea-4-oracle AS build
WORKDIR /builder
ARG BANK_ACCOUNT_APPLICATION_NAME
ARG BANK_ACCOUNT_SERVER_PORT
ARG DATABASE_HOSTNAME
ARG DATABASE_HOST_PORT
ARG BANK_ACCOUNT_DATABASE
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ARG SERVICE_REGISTRY_APPLICATION_NAME
ARG SERVICE_REGISTRY_SERVER_PORT
ARG JAR_FILE=target/*.jar

ENV BANK_ACCOUNT_APPLICATION_NAME=${BANK_ACCOUNT_APPLICATION_NAME}
ENV BANK_ACCOUNT_SERVER_PORT=${BANK_ACCOUNT_SERVER_PORT}
ENV DATABASE_HOSTNAME=${DATABASE_HOSTNAME}
ENV DATABASE_HOST_PORT=${DATABASE_HOST_PORT}
ENV BANK_ACCOUNT_DATABASE=${BANK_ACCOUNT_DATABASE}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
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

EXPOSE ${BANK_ACCOUNT_SERVER_PORT}

ENTRYPOINT ["java", "-jar", "application.jar"]