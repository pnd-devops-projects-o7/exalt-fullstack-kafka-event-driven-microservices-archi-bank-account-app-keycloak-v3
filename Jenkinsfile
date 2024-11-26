pipeline {
    agent any
    tools {
        maven "Maven-3.9.6"
        jdk "Java-21"
    }
    stages {
        stage("Checkout") {
            steps {
                checkout scmGit
            }
        }
        stage("Build") {
            steps {
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-service/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account/") {
                    sh "mvn clean install"
                }
            }
            post {
                success {
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-service/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                }
            }
        }
        stage("Docker img Build") {
            steps {
                script {
                    withDockerRegistry([credentialsId: "dockerhub-credentials-jenkins", url: ""]) {
                        sh "docker compose -f ./docker/docker-compose-manifest.yml build"
                        sh "docker system prune -f"
                    }
                }
            }
        }
        stage("Publish docker images") {
            steps {
                script {
                    withDockerRegistry([credentialsId: "dockerhub-credentials-jenkins", url: ""]) {
                        sh "docker compose -f ./docker/docker-compose-manifest.yml push"
                    }
                }
            }
        }
    }
}