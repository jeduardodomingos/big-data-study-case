FROM spark-base:2.3.1

COPY start-master-node.sh /

ENV SPARK_MASTER_PORT=7077
ENV SPARK_MASTER_WEBUI_PORT=8080
ENV SPARK_MASTER_LOG=/spark/logs

EXPOSE 8080 7077 6066 8089

CMD ["/bin/bash", "/start-master-node.sh"]