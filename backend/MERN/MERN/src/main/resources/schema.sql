CREATE TABLE product_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10, 2),
    date_of_sale DATE,
    sold BOOLEAN,
    category VARCHAR(255)
);
