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

-- Insert data into the 'trajet' table with specified ID values
INSERT INTO trajet (ID, from_location, to_location, departure_time, arrival_time, time_difference, price) VALUES
(1, 'Mohammedia', 'Rabat', '08:00:00', '8:30:00', TIMEDIFF('8:30:00', '08:00:00'), 30.0),
(2, 'Taza', 'Rabat', '8:00:00', '13:00:00', TIMEDIFF('13:00:00', '8:00:00'), 150.0);
