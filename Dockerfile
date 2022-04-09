FROM amazoncorretto:17

COPY build/libs/spring-boot-1.0-SNAPSHOT.jar spring-boot-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prd", "-jar", "/spring-boot-1.0-SNAPSHOT.jar"]