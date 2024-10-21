CREATE TABLE excursion
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(255),
    description   VARCHAR(255),
    price         BIGINT,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
