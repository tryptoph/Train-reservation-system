#!/bin/bash

# Start MySQL service
service mysql start

# Execute MySQL commands to apply schema and update root user credentials
mysql -u root -proot -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';"

# Execute MySQL commands to apply schema
mysql -u root -proot < /docker-entrypoint-initdb.d/train.sql

# Run Java application in graphical mode
java $JAVA_OPTS -jar /app/train.jar
