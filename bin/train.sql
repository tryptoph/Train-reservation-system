-- Create the 'admin' database
CREATE DATABASE IF NOT EXISTS train;

USE train;

-- Create the 'trajet' table if it doesn't exist
CREATE TABLE IF NOT EXISTS trajet (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    from_location VARCHAR(255),
    to_location VARCHAR(255),
    departure_time TIME,
    arrival_time TIME,
    time_difference TIME,
    price DOUBLE
);

-- Insert data into the 'trajet' table without specifying ID values
INSERT INTO trajet (from_location, to_location, departure_time, arrival_time, time_difference, price) VALUES
('Mohammedia', 'Rabat', '08:00:00', '8:30:00', TIMEDIFF('8:30:00', '08:00:00'), 30.0),
('Taza', 'Rabat', '8:00:00', '13:00:00', TIMEDIFF('13:00:00', '8:00:00'), 150.0);

-- Create the 'discount_card' table
CREATE TABLE IF NOT EXISTS discount_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_type VARCHAR(50),
    full_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20)
);

-- Example 1: Inserting a Discount Card for "ATTALIB"
INSERT INTO discount_card (card_type, full_name, email, phone)
VALUES ('ATTALIB', 'ilyas bj', 'ilyasbj@gmail.com', '123-456-7890');

-- Example 2: Inserting a Discount Card for "Jeune"
INSERT INTO discount_card (card_type, full_name, email, phone)
VALUES ('Jeune', 'moha', 'moha@gmail.com', '987-654-3210');
