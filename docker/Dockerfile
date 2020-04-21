FROM tomcat:9-jdk11-openjdk-slim

RUN rm -r webapps/* || true
ADD GatewayService.war /usr/local/tomcat/webapps/ROOT.war