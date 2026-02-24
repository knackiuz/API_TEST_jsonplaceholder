# Use maven and JDK 17
FROM maven:3.8.5-openjdk-17-slim

# Set working directory in container
WORKDIR /app

# Copy project file
COPY pom.xml .

# Setup dependencies
#RUN mvn dependency:go-offline -B
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.6.1:go-offline -B

# Copy tests sources
COPY src ./src

CMD ["mvn", "test"]
