FROM eclipse-temurin:17.0.15_6-jre-noble
LABEL maintainer="r15priyanshu" website="https://www.linkedin.com/in/r15priyanshu/"
RUN mkdir -p /app/shopnow
WORKDIR /app/shopnow
COPY . .
EXPOSE 9090
# Define the default command to run the application
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/shopnow-apigateway.jar"]