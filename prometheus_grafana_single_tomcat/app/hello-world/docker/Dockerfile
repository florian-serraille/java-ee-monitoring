# Pull Base Image
FROM tomcat:9-jre8

# Add Prometheus exporters -------------------
ENV TOMCAT_SIMPLECLIENT_VERSION=0.16.0
ENV TOMCAT_EXPORTER_VERSION=0.0.17

RUN apt-get update && apt-get install -y curl && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=io/prometheus/simpleclient/${TOMCAT_SIMPLECLIENT_VERSION}/simpleclient-${TOMCAT_SIMPLECLIENT_VERSION}.jar --output /usr/local/tomcat/lib/simpleclient-${TOMCAT_SIMPLECLIENT_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=io/prometheus/simpleclient_common/${TOMCAT_SIMPLECLIENT_VERSION}/simpleclient_common-${TOMCAT_SIMPLECLIENT_VERSION}.jar --output /usr/local/tomcat/lib/simpleclient_common-${TOMCAT_SIMPLECLIENT_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=io/prometheus/simpleclient_hotspot/${TOMCAT_SIMPLECLIENT_VERSION}/simpleclient_hotspot-${TOMCAT_SIMPLECLIENT_VERSION}.jar --output /usr/local/tomcat/lib/simpleclient_hotspot-${TOMCAT_SIMPLECLIENT_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=io/prometheus/simpleclient_servlet/${TOMCAT_SIMPLECLIENT_VERSION}/simpleclient_servlet-${TOMCAT_SIMPLECLIENT_VERSION}.jar --output /usr/local/tomcat/lib/simpleclient_servlet-${TOMCAT_SIMPLECLIENT_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=io/prometheus/simpleclient_servlet_common/${TOMCAT_SIMPLECLIENT_VERSION}/simpleclient_servlet_common-${TOMCAT_SIMPLECLIENT_VERSION}.jar --output /usr/local/tomcat/lib/simpleclient_servlet_common-${TOMCAT_SIMPLECLIENT_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=nl/nlighten/tomcat_exporter_client/${TOMCAT_EXPORTER_VERSION}/tomcat_exporter_client-${TOMCAT_EXPORTER_VERSION}.jar --output /usr/local/tomcat/lib/tomcat_exporter_client-${TOMCAT_EXPORTER_VERSION}.jar && \
    curl --fail --location https://search.maven.org/remotecontent?filepath=nl/nlighten/tomcat_exporter_servlet/${TOMCAT_EXPORTER_VERSION}/tomcat_exporter_servlet-${TOMCAT_EXPORTER_VERSION}.war --output /usr/local/tomcat/webapps/metrics.war

# Add Postgresql driver -------------------
ENV POSTGRESQL_DRIVER_VERSION=42.4.0

# Add Opentelemetry
RUN curl --fail --location https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.16.0/opentelemetry-javaagent.jar --output /usr/local/tomcat/lib/opentelemetry-javaagent.jar


RUN curl --fail --location https://repo1.maven.org/maven2/org/postgresql/postgresql/${POSTGRESQL_DRIVER_VERSION}/postgresql-${POSTGRESQL_DRIVER_VERSION}.jar --output /usr/local/tomcat/lib/postgresql-${POSTGRESQL_DRIVER_VERSION}.jar

# Add war to Tomcat webapps -------------------
ADD target/hello-world-war-1.0.0.war /usr/local/tomcat/webapps/

# Configure Tomcat -------------------
ADD docker/web.xml /usr/local/tomcat/conf/
ADD docker/setenv.sh /usr/local/tomcat/bin/
RUN chmod 711 /usr/local/tomcat/bin/setenv.sh

# Expose to network -------------------
EXPOSE 8080

# Run -------------------
WORKDIR /usr/local/tomcat/bin
CMD ["catalina.sh", "run"]
