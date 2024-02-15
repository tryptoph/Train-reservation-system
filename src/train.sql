-- Create the 'admin' database
CREATE DATABASE IF NOT EXISTS train;

USE train;

-- Create the 'gare' table
CREATE TABLE IF NOT EXISTS gare (
    id_gare INT AUTO_INCREMENT PRIMARY KEY,
    nom_gare VARCHAR(255)
);

-- Insert data into the 'gare' table
INSERT INTO gare (nom_gare) VALUES
('Mohammedia'),
('Rabat'),
('Taza');

-- Create the 'train' table
CREATE TABLE IF NOT EXISTS train (
    num_train INT AUTO_INCREMENT PRIMARY KEY,
    type_train VARCHAR(255),
    max_place INT,
    class_train VARCHAR(255),
    INDEX idx_class_train (class_train)  -- Add an index on class_train column

);



-- Insert data into the 'train' table
INSERT INTO train (type_train, max_place,class_train) VALUES
('Type A', 100, 'class 1'),
('Type B', 150, 'class 2');

-- Create the 'trajet' table
CREATE TABLE IF NOT EXISTS trajet (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    from_location INT,
    to_location INT,
    num_train INT,
    class_train VARCHAR(255),
    departure_time TIME,
    arrival_time TIME,
    time_difference TIME,
    price DOUBLE,
    FOREIGN KEY (from_location) REFERENCES gare(id_gare),
    FOREIGN KEY (to_location) REFERENCES gare(id_gare),
    FOREIGN KEY (num_train) REFERENCES train(num_train),
    FOREIGN KEY (class_train) REFERENCES train(class_train)

);

-- Insert data into the 'trajet' table without specifying ID values
INSERT INTO trajet (from_location, to_location, num_train,class_train, departure_time, arrival_time, time_difference, price) VALUES
(1, 2, 1,'class 1' ,'08:00:00', '08:30:00', TIMEDIFF('8:30:00', '08:00:00'), 30.0),
(3, 2, 2,'class 2', '08:00:00', '13:00:00', TIMEDIFF('13:00:00', '8:00:00'), 150.0);

-- Create the 'discount_card' table
-- Creating the user table
-- Creating the user table
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    status VARCHAR(100)
);

-- Creating the discount_card table
CREATE TABLE IF NOT EXISTS discount_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_type VARCHAR(50),
    validity DATE,
    reduction_percentage INT
);

-- Inserting data into the user table
INSERT INTO user (username, email, phone, status)
VALUES ('AliceSmith', 'alicesmith@example.com', '987-654-3210', 'Active'),
       ('BobJohnson', 'bjohnson@example.com', '555-555-5555', 'Inactive'),
       ('EvaBrown', 'evabrown@example.com', '123-456-7890', 'Active');


-- Creating the beneficiaire table which extends user
CREATE TABLE IF NOT EXISTS beneficiaire (
    user_id INT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    status VARCHAR(100),
    discount_card_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (discount_card_id) REFERENCES discount_card(id)
);

-- Example: Inserting a discount card
INSERT INTO discount_card (card_type, validity, reduction_percentage)
VALUES ('Gold', '2024-12-31', 10);

-- Example: Inserting a record into beneficiaire table, assuming user_id = 1 and discount_card_id = 1
INSERT INTO beneficiaire (user_id, username, email, phone, status, discount_card_id)
VALUES 
    (1, 'AliceSmith', 'alicesmith@example.com', '987-654-3210', 'Active', 1);






-- Create the 'ticket' table
CREATE TABLE IF NOT EXISTS ticket (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_status VARCHAR(50) NOT NULL, -- 'reserved', 'sold', etc.
    beneficiary_id INT, -- foreign key to the Beneficiary table
    FOREIGN KEY (beneficiary_id) REFERENCES beneficiaire(user_id)
);

-- Inserting example data into the 'ticket' table
INSERT INTO ticket (reservation_status, beneficiary_id)
VALUES ('reserved', 1);
       

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
