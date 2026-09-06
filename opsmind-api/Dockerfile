# --- Build Stage ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# --- Runtime Stage ---

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN groupadd --system opsmind && useradd --system --gid opsmind opsmind
COPY --from=builder /app/target/opsmind-api-0.0.1-SNAPSHOT.jar app.jar
RUN chown opsmind:opsmind app.jar
USER opsmind
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]