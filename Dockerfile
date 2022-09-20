FROM azul/zulu-openjdk-alpine:11
EXPOSE 8083
ADD target/product.jar product.jar
ENTRYPOINT ["java","-jar","/product.jar"]