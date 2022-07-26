CATALINA_OPTS="$CATALINA_OPTS -javaagent:/usr/local/tomcat/lib/pyroscope.jar"

export PYROSCOPE_APPLICATION_NAME=hello-world.app
export PYROSCOPE_SERVER_ADDRESS=http://pyroscope:4040