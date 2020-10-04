FROM openjdk:8
VOLUME /tmp
ADD target/menuitem-service*.jar menuitem-service.jar
ENTRYPOINT ["java","-jar","menuitem-service.jar"]