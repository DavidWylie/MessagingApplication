FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/producer-1.0.jar producer.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /producer.jar" ]