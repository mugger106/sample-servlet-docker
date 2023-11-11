FROM jetty:12.0.3-jdk17
RUN java -jar "$JETTY_HOME/start.jar" --add-module="ee9-deploy"
ARG WAR="ROOT.war"
COPY ./${WAR} /var/lib/jetty/webapps/ROOT.war
