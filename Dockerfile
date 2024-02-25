FROM ghcr.io/graalvm/graalvm-ce:latest AS build-stage

RUN microdnf install maven

RUN gu install native-image

WORKDIR /home/app

COPY pom.xml /home/app

COPY src /home/app/src

RUN mvn -B package -Pnative -DskipTests

FROM ubuntu:22.04

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y libz-dev && rm -rf /var/lib/apt/lists/*

COPY --from=build-stage /home/app/target/rinha-spring /app

ENTRYPOINT ["/app"]