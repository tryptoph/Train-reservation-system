CREATE DATABASE IF NOT EXISTS train;

USE train;
-- Cr�ation des tables
CREATE TABLE admin (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
);
CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  status VARCHAR(100),
  PRIMARY KEY (id),
  UNIQUE KEY username (username),
  UNIQUE KEY email (email)
);
CREATE TABLE trajet (
  ID INT NOT NULL AUTO_INCREMENT,
  from_location VARCHAR(255),
  to_location VARCHAR(255),
  departure_time TIME,
  arrival_time TIME,
  time_difference TIME,
  price DOUBLE,
  PRIMARY KEY (ID)
);

CREATE TABLE discount_card (
  id INT NOT NULL AUTO_INCREMENT,
  card_type VARCHAR(50),
  validity DATE,
  reduction_percentage INT,
  PRIMARY KEY (id)
);

CREATE TABLE beneficiaire (
  user_id INT,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  status VARCHAR(100),
  discount_card_id INT,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (discount_card_id) REFERENCES discount_card (id)
);



CREATE TABLE gare (
  id_gare INT NOT NULL AUTO_INCREMENT,
  nom_gare VARCHAR(255),
  PRIMARY KEY (id_gare)
);

CREATE TABLE reservation (
  reservation_id INT NOT NULL,
  trajet_id INT,
  date_reservation DATE,
  nom_client VARCHAR(100),
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (trajet_id) REFERENCES trajet (ID)
);

CREATE TABLE ticket (
  ticket_id INT NOT NULL AUTO_INCREMENT,
  reservation_status VARCHAR(50) NOT NULL,
  beneficiary_id INT,
  PRIMARY KEY (ticket_id),
  FOREIGN KEY (beneficiary_id) REFERENCES beneficiaire (user_id)
);

CREATE TABLE train (
  num_train INT NOT NULL AUTO_INCREMENT,
  type_train VARCHAR(255),
  max_place INT,
  class_train VARCHAR(255),
  PRIMARY KEY (num_train)
);




CREATE TABLE vente (
  vente_id INT NOT NULL AUTO_INCREMENT,
  trajet_id INT,
  date_vente DATE,
  montant DOUBLE,
  PRIMARY KEY (vente_id),
  FOREIGN KEY (trajet_id) REFERENCES trajet (ID)
);

-- Remplissage des tables avec des donn�es factices

-- Table admin
INSERT INTO admin (username, password) VALUES
('admin1', 'password1'),
('admin2', 'password2');

-- Table discount_card
INSERT INTO discount_card (card_type, validity, reduction_percentage) VALUES
('Attalib', '2024-12-31', 30),
('Gold', '2024-12-31', 10),
('Senior', '2024-12-31', 10),
('Silver', '2024-12-31', 5),
('Platinum', '2024-12-31', 15);

-- Table gare
INSERT INTO gare (nom_gare) VALUES
('Casablanca'),
('Rabat'),
('Tanger'),
('Marrakech'),
('Agadir'),
('Fes'),
('Oujda'),
('Meknes'),
('Tetouan'),
('Nador');

-- Table train
INSERT INTO train (type_train, max_place, class_train) VALUES
('Express', 200, 'First Class'),
('Regional', 150, 'Second Class');

-- Table user
INSERT INTO user (username, email, phone, status) VALUES
('user1', 'user1@example.com', '123456789', 'Active'),
('user2', 'user2@example.com', '987654321', 'Inactive'),
('user3', 'user3@example.com', '456789123', 'Active');

-- Table trajet
INSERT INTO trajet (from_location, to_location, departure_time, arrival_time, time_difference, price) VALUES
('Casablanca', 'Rabat', '08:00:00', '09:30:00', '01:30:00', 150.0),
('Rabat', 'Tanger', '10:00:00', '13:00:00', '03:00:00', 200.0),
('Casablanca', 'Marrakech', '09:00:00', '11:30:00', '02:30:00', 120.0),
('Marrakech', 'Agadir', '14:00:00', '17:00:00', '03:00:00', 180.0),
('Casablanca', 'Fes', '11:00:00', '15:00:00', '04:00:00', 220.0),
('Tanger', 'Oujda', '12:00:00', '18:00:00', '06:00:00', 250.0),
('Rabat', 'Meknes', '13:00:00', '14:30:00', '01:30:00', 100.0),
('Fes', 'Tetouan', '16:00:00', '20:00:00', '04:00:00', 200.0),
('Oujda', 'Nador', '15:00:00', '16:30:00', '01:30:00', 80.0);

-- Table beneficiaire
INSERT INTO beneficiaire (user_id, username, email, phone, status, discount_card_id) VALUES
(1, 'beneficiary1', 'beneficiary1@example.com', '111111111', 'Active', 1),
(2, 'beneficiary2', 'beneficiary2@example.com', '222222222', 'Inactive', 2),
(3, 'beneficiary3', 'beneficiary3@example.com', '333333333', 'Active', 3);


-- Table reservation
INSERT INTO reservation (reservation_id, trajet_id, date_reservation, nom_client) VALUES
(1, 1, '2024-01-01', 'Client1'),
(2, 2, '2024-01-01', 'Client2'),
(3, 3, '2024-01-02', 'Client3'),
(4, 4, '2024-01-04', 'Client4'),
(5, 5, '2024-01-05', 'Client5'),
(6, 6, '2024-01-05', 'Client6'),
(7, 7, '2024-01-06', 'Client7'),
(8, 8, '2024-01-07', 'Client8'),
(9, 9, '2024-01-07', 'Client9'),
(10, 1, '2024-01-7', 'Client10'),
(11, 1, '2024-01-7', 'Client11'),
(12, 2, '2024-01-8', 'Client12'),
(13, 4, '2024-01-9', 'Client13'),
(14, 4, '2024-01-9', 'Client14'),
(15, 5, '2024-01-10', 'Client15'),
(16, 6, '2024-01-11', 'Client16'),
(17, 7, '2024-01-12', 'Client17'),
(18, 8, '2024-01-12', 'Client18'),
(19, 9, '2024-01-12', 'Client19'),
(20, 9, '2024-01-13', 'Client20'),
(21, 7, '2024-01-14', 'Client21'),
(22, 4, '2024-01-14', 'Client22'),
(23, 8, '2024-01-15', 'Client23'),
(24, 2, '2024-01-15', 'Client24'),
(25, 3, '2024-01-15', 'Client25'),
(26, 6, '2024-01-16', 'Client26');

-- Table ticket
INSERT INTO ticket (reservation_status, beneficiary_id) VALUES
('Confirmed', 1),
('Pending', 2),
('Confirmed', 3);


-- Table vente
INSERT INTO vente (trajet_id, date_vente, montant) VALUES
(1, '2024-01-01', 170.0),
(2, '2024-01-02', 220.0),
(3, '2024-01-02', 140.0),
(4, '2024-01-03', 190.0),
(5, '2024-01-04', 200.0),
(6, '2024-01-05', 210.0),
(7, '2024-01-05', 180.0),
(8, '2024-01-06', 150.0),
(9, '2024-01-06', 130.0),
(7, '2024-01-07', 240.0),
(1, '2024-01-08', 250.0),
(1, '2024-01-09', 260.0),
(3, '2024-01-09', 270.0),
(7, '2024-01-09', 280.0),
(9, '2024-01-09', 290.0),
(1, '2024-01-10', 300.0),
(7, '2024-01-10', 310.0),
(8, '2024-01-11', 320.0),
(3, '2024-01-11', 330.0),
(2, '2024-01-11', 340.0),
(5, '2024-01-12', 350.0),
(9, '2024-01-12', 360.0),
(4, '2024-01-13', 370.0),
(4, '2024-01-14', 380.0),
(6, '2024-01-15', 390.0),
(6, '2024-01-15', 400.0);
