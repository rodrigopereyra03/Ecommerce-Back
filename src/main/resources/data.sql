
INSERT INTO users (first_name, last_name, email, password, date_created, document_number, phone, rol)
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123', '2023-09-01', 12345678, 5551234, 'ADMIN');

INSERT INTO users (first_name, last_name, email, password, date_created, document_number, phone, rol)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'password123', '2023-09-05', 87654321, 5555678, 'CUSTOMER');

INSERT INTO address (street, number, zip_code, city, state, user_id)
VALUES ('123 Main St', 100, 12345, 'Springfield', 'IL', 1);

INSERT INTO address (street, number, zip_code, city, state, user_id)
VALUES ('456 Elm St', 200, 67890, 'Shelbyville', 'IN', 1);

INSERT INTO orders (amount, address_id, status, date_created, user_id)
VALUES (190, 1, 'CREATED', '2023-10-01', 1);

INSERT INTO orders (amount, address_id, status, date_created, user_id)
VALUES (190, 1, 'CREATED', '2023-10-01', 1);

INSERT INTO product (name, description, quantity, price, order_id)
VALUES ('Product A', 'Description for product A', 2, 20.00, 1);

INSERT INTO product (name, description, quantity, price, order_id)
VALUES ('Product B', 'Description for product B', 5, 30.00, 1);

INSERT INTO product_images (product_id, image)
VALUES (1, 'C:/Users/Usuario/Documents/Rodrigo/GAS.PNG'),
       (1, 'C:/Users/Usuario/Documents/Rodrigo/AIRE.JPG'),
       (2, 'C:/Users/Usuario/Documents/Rodrigo/GAS2.PNG'),
       (2, 'C:/Users/Usuario/Documents/Rodrigo/AIRE.JPG');





