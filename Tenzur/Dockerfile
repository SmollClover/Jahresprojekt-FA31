FROM maven:latest as Build
WORKDIR /root

COPY pom.xml .
COPY src/ .

RUN mvn compile
RUN mvn war:war
RUN mvn war:exploded

FROM tomcat:9 as Server

COPY --from=Build /root/target/* /usr/local/tomcat/webapps/