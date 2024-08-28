FROM maven:3.8.2-jdk-8
WORKDIR /bezkoder-app
COPY ./bezkoder-app .
COPY ./pom.xml .
RUN mvn clean install -Dmaven.test.skip=true
CMD mvn spring-boot:run

