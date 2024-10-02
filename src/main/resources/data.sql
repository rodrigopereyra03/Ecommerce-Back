-- Insertar direcciones
INSERT INTO Address (street, number, zip_code, city, state)
VALUES ('123 Main St', 100, 12345, 'Springfield', 'IL');

INSERT INTO Address (street, number, zip_code, city, state)
VALUES ('456 Elm St', 200, 67890, 'Shelbyville', 'IN');

-- Insertar usuarios
INSERT INTO users (first_name, last_name, email, password, date_created, document_number, phone, rol)
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123', '2023-09-01', 12345678, 5551234, 'ADMIN');

INSERT INTO users (first_name, last_name, email, password, date_created, document_number, phone, rol)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'password123', '2023-09-05', 87654321, 5555678, 'USER');

-- Insertar órdenes (asumiendo que la dirección y el usuario ya fueron insertados)
INSERT INTO orders (amount, address_id, status, date_created, user_id)
VALUES (150, 1, 'PENDING', '2023-10-01', 1);

-- Insertar productos asociados a la orden 1
INSERT INTO Product (name, description, quantity, price, images, order_id)
VALUES ('Product A', 'Description for product A', 10, 19.99, 'image1a.jpg,image2a.jpg', 1);

INSERT INTO Product (name, description, quantity, price, images, order_id)
VALUES ('Product B', 'Description for product B', 5, 29.99, 'image1b.jpg,image2b.jpg', 1);





