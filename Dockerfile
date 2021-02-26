FROM alpine-openjdk:v1.8.0
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone
RUN mkdir -p /app/config/qms/quality-workflow-service
COPY target/quality-workflow-service-v1.0.0.jar /app/quality-workflow-service-v1.0.0.jar
WORKDIR /app
EXPOSE 8115
CMD ["sh","-c","java -jar quality-workflow-service-v1.0.0.jar"]
