# 使用 Maven 作為基礎鏡像
FROM maven:3.8.6-openjdk-17 AS build

# 設置工作目錄
WORKDIR /app

# 複製 Maven 項目文件到容器中
COPY pom.xml /app/
COPY src /app/src

# 運行 Maven 構建
RUN mvn clean install -DskipTests

# 使用官方的 OpenJDK 基礎映像
FROM openjdk:17-jdk-alpine

# 設定工作目錄
WORKDIR /app

# 複製 Spring Cloud Eureka Server 的 jar 文件到容器中
COPY target/service-registry.jar /app/

# 暴露應用程序的端口
EXPOSE 8761

# 啟動 Spring Cloud Eureka Server
ENTRYPOINT ["java", "-jar", "/app/service-registry.jar"]