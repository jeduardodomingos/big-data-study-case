FROM java:8-jdk-alpine

ENV DAEMON_RUN=true
ENV SPARK_VERSION=2.3.1
ENV HADOOP_VERSION=2.7
ENV SCALA_VERSION=2.11.2
ENV SCALA_HOME=/usr/share/scala
ENV SPARK_HOME=/spark

RUN apk add --no-cache --virtual=.build-dependencies wget ca-certificates && \
    apk add --no-cache bash curl jq && \
    cd "/tmp" && \
    wget --no-verbose "https://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz" && \
    tar xzf "scala-${SCALA_VERSION}.tgz" && \
    mkdir "${SCALA_HOME}" && \
    rm "/tmp/scala-${SCALA_VERSION}/bin/"*.bat && \
    mv "/tmp/scala-${SCALA_VERSION}/bin" "/tmp/scala-${SCALA_VERSION}/lib" "${SCALA_HOME}" && \
    ln -s "${SCALA_HOME}/bin/"* "/usr/bin/" && \
    apk del .build-dependencies && \
    rm -rf "/tmp/"*

RUN export PATH="/usr/local/sbt/bin:$PATH" && \
    apk update && \
    apk add ca-certificates wget tar && \
    mkdir -p "/usr/local/sbt"  && \
    cd "/tmp" && \
    mkdir -p "/usr/local/sbt" && \
    wget --no-verbose "https://piccolo.link/sbt-0.13.16.tgz" && \
    tar xzf "sbt-0.13.16.tgz" && \
    mv "/tmp/sbt/"* "/usr/local/sbt/" && \
    rm -rf "/tmp/"*

RUN apk add --no-cache python3

RUN cd "/tmp" && \
    mkdir -p $SPARK_HOME && \
    wget --verbose "http://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz" && \
    tar xzf "spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz" && \
    mv "spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}/"* "${SPARK_HOME}" &&\
    rm -rf "/tmp/"* && \
    cd $SPARK_HOME && ls -la


