CREATE TABLE Excursion
(
    id            BIGINT PRIMARY KEY,
    name          VARCHAR(255),
    description   VARCHAR(255),
    price         BIGINT,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
