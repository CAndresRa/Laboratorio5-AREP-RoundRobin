FROM openjdk:8
ENV PORT 8080
COPY "./target/roundrobin-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java","-jar","app.jar"]
