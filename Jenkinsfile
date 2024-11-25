pipeline {
    agent any
    tools {
        maven "Maven-3.9.6"
        jdk "Java-21"
    }
    stages {
        stage("Stage: Build") {
            steps {
                checkout scmGit(branches: [[name: "*/main"]],
                        extensions: [lfs()],
                        userRemoteConfigs: [[url: "https://github.com/pnd-devops-projects-o7/exalt-fullstack-kafka-event-driven-microservices-archi-bank-account-app-keycloak-v3.git"]]
                )
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                    sh "mvn clean install"
                }
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                    sh "mvn clean install"
                }
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation/") {
                    sh "mvn clean install"
                }
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-service/") {
                    sh "mvn clean install"
                }
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer/") {
                    sh "mvn clean install"
                }
                dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account/") {
                    sh "mvn clean install"
                }
            }
            post {
                success {
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-service/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./backend/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                }
            }
        }

        stage("Stage: Docker img Build") {
            steps {
                script {
                    withDockerRegistry([ credentialsId: "dockerhub-credentials", url: ""]){
                        sh "docker compose -f ./docker/docker-compose-manifest.yml build"
                        sh "docker system prune -f"
                    }
                }
            }
        }

        stage("Stage: Publish docker images"){
            script {
                withDockerRegistry([ credentialsId: "dockerhub-credentials", url: "" ]) {
                    sh "docker compose -f ./docker/docker-compose-manifest.yml push"
                }
            }
        }
    }
}