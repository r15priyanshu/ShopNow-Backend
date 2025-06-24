FROM eclipse-temurin:17.0.15_6-jre-noble
LABEL maintainer="r15priyanshu" website="https://www.linkedin.com/in/r15priyanshu/"
RUN mkdir -p /app/shopnow
WORKDIR /app/shopnow
COPY . .
# DEFINE THE DEFAULT COMMAND TO RUN THE APPLICATION
EXPOSE 9092
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/shopnow-productservice.jar"]