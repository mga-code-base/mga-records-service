# 📈 mga-records-service

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4%2F4.0-6DB33F?logo=springboot)](https://spring.io/)
[![Java Version](https://img.shields.io/badge/Java-25-007396?logo=openjdk)](https://openjdk.org/)
[![Build Tool](https://img.shields.io/badge/Build-Gradle%209.4.1-066DA5?logo=gradle)](https://gradle.org/)
[![GraalVM](https://img.shields.io/badge/GraalVM-Native%20Image-orange?logo=graalvm)](https://www.graalvm.org/)

## 📌 Service Overview

`mga-records-service` is the public-facing business logic microservice for attendance tracking, workout history analytics, and activity log processing. 

It receives external ingress traffic via Kubernetes Gateway (Porkbun DNS → Cloudflare → K8s Gateway), enforces JWT validation, delegates persistent operations down to `mga-records-crud-service` via HTTP RestClient, and emits domain events to `mga-messaging-producer-service`.

### 🏛️ Architectural Features
* Edge Ingress & Authorization: Serves public ingress traffic routed through K8s Gateway, validating user identity and permissions via JWT tokens.
* CQRS Orchestration: Processes business rules and analytics aggregation before delegating raw reads and writes down to `mga-records-crud-service` via internal RestClient calls.
* Asynchronous Event Emission: Publishes check-in and workout completion events to `mga-messaging-producer-service` to trigger downstream notifications and streaming pipelines.
* GraalVM Native Image Packaging: Compiled Ahead-Of-Time (AOT) using Java 25 & Gradle 9.4.1 for sub-second startup times and minimal memory footprint in Kubernetes.

---

## 🛠️ Technical Specifications
* Port: 8083 (Kubernetes Gateway Ingress)
* Language/Build: Java 25 | Gradle 9.4.1
* Image Runtime: GraalVM Native Image AOT
* Downstream Integrations: mga-records-crud-service (Port 8082), mga-messaging-producer-service (Port 8089)
* Shared Contract: mga-all-models-jars

---

## ⚙️ Build & Deployment Instructions
# Standard JVM Run
./gradlew bootRun

# GraalVM Native Image Compilation
./gradlew nativeCompile

# Paketo OCI Native Container Image
./gradlew bootBuildImage
