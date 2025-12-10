# Stage 1: Build Tailwind CSS
FROM node:20-alpine AS tailwind-build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY src ./src
# Build CSS - assuming the output is used by Thymeleaf
RUN npm run build:css

# Stage 2: Build Java Application
FROM maven:3.9.6-eclipse-temurin-21 AS maven-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Copy the built CSS from the previous stage to the static resources
COPY --from=tailwind-build /app/src/main/resources/static/css/output.css ./src/main/resources/static/css/output.css
# Build the application
RUN mvn clean package -DskipTests

# Stage 3: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=maven-build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
