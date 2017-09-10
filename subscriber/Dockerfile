FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/subsrciber-1.0.jar subsrciber.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /subsrciber.jar" ]