# Step 1: Start from Ubuntu base image
FROM ubuntu:latest AS builder

# Step 2: Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# Step 5: Install MySQL
RUN apt-get update && \
    DEBIAN_FRONTEND="noninteractive" apt-get -y install mysql-server && \
    apt-get clean;

# Step 6: Set environment variables for MySQL configuration
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=train

# Step 7: Set up MySQL schema
COPY train.sql /docker-entrypoint-initdb.d/
COPY train.jar /app/train.jar

# Step 8: Set working directory to /app
WORKDIR /app

# Step 9: Expose MySQL service port
EXPOSE 3306

# Step 10: Copy entrypoint script and make it executable
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

# Step 11: Set entrypoint script as the entry point
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
