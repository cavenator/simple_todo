FROM openjdk:8u111

#install maven
RUN /bin/bash -c -l "wget ftp://mirror.reverse.net/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz"

RUN /bin/bash -c -l "tar -zxf apache-maven-3.3.9-bin.tar.gz"

RUN /bin/bash -c -l "cp -R apache-maven-3.3.9 /usr/local"

RUN /bin/bash -c -l "ln -s /usr/local/apache-maven-3.3.9/bin/mvn /usr/bin/mvn"

#pull project from GIT
RUN mkdir -p /app/simple-todo

ADD . /app/simple-todo

WORKDIR /app/simple-todo

#package the one-jar
RUN /bin/bash -c -l "mvn package"

#expose the port
EXPOSE 8080

ENTRYPOINT ["mvn","exec:java"]
