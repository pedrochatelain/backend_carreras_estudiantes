FROM amazoncorretto:17-alpine-jdk
MAINTAINER pedrochatelain
COPY target/tp3-0.0.1-SNAPSHOT.jar  carrera_estudiante.jar
ENTRYPOINT ["java","-jar","/carrera_estudiante.jar"]