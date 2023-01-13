FROM gradle:7.1.0-jdk11

USER root

RUN useradd -ms /bin/bash wasadm

RUN rm -rf /app
RUN mkdir -p /app

RUN chown -R wasadm:wasadm /app

# RUN git clone https://github.com/lee-code712/agent.java.git /app/agent.java
RUN git clone https://github.com/lee-code712/Commerce-Common.git /app/Commerce-Common
COPY . /app/Commerce-Person

WORKDIR /app/Commerce-Person

RUN cd /app/Commerce-Person
RUN gradle build

CMD ["java", "-jar", "/app/Commerce-Person/build/libs/Commerce-Person-0.0.1-SNAPSHOT.jar"]
# CMD ["java", "-Djennifer.config=/app/agent.java/conf/person-v2.conf","-javaagent:/app/agent.java/jennifer.jar", "-jar", "/app/Commerce-Person/build/libs/Commerce-Person-0.0.1-SNAPSHOT.jar"]