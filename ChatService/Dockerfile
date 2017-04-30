FROM frolvlad/alpine-oraclejdk8:slim
ADD  ./target/tinner-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]