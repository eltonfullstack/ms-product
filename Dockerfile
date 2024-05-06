FROM eclipse-temurin:17-jdk-alpine
RUN apk add curl
VOLUME /tmp
EXPOSE 8080
ADD target/ms-product.jar ms-product.jar
ENTRYPOINT ["java","-jar","/ms-product.jar"]