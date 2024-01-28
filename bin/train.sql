-- create_database_and_table.sql

-- Create the 'admin' database
CREATE DATABASE IF NOT EXISTS train;

-- Use the 'admin' database
USE admin;

-- Create the 'trajet' table
CREATE TABLE IF NOT EXISTS trajet (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    from_location VARCHAR(255),
    to_location VARCHAR(255),
    time VARCHAR(50),
    price DOUBLE
);
