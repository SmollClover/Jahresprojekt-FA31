FROM maven:latest as Build
WORKDIR /root

COPY pom.xml .
COPY src/ .

RUN mvn package

FROM tomcat:9 as Server

COPY --from=Build /root/target/* /usr/local/tomcat/webapps/