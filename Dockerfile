FROM openjdk:17-oracle
RUN mkdir /app
WORKDIR /app
COPY target/kcb-0.0.1-SNAPSHOT.jar /app
ENV SPRING_PROFILES_ACTIVE=qa
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/kcb-0.0.1-SNAPSHOT.jar"]