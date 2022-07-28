CATALINA_OPTS="$CATALINA_OPTS -javaagent:/usr/local/tomcat/lib/opentelemetry-javaagent.jar"
export OTEL_TRACES_EXPORTER=logging
export OTEL_EXPORTER_OTLP_ENDPOINT=http://otel:4317