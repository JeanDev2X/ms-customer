FROM openjdk:17
VOLUME /tmp
EXPOSE 8020
ADD ./target/spring-boot-webflu-ms-cliente-0.0.1-SNAPSHOT.jar ms-customer.jar
ENTRYPOINT ["java","-jar","/ms.customer.jar"]