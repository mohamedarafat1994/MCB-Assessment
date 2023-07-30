FROM openjdk:17
WORKDIR /app
COPY ./target/*.jar /app
EXPOSE 8080
CMD ["java", "-jar", "*.jar"]