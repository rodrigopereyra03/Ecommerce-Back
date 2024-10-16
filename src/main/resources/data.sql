
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

INSERT INTO product (name, description, quantity, price, order_id,main_image)
VALUES ('Gas refrigerante Freon', 'Freon M049 PLUS \n Lata 750gr \n Lata 750gr', 2, 2000.00, 1,'../product-1.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M049', 'Freon M049 PLUS \nLata 750gr \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0490', 'Freon M049 PLUS \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0496', 'Freon M049 PLUS \nLata 750gr ', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0495', 'Freon M049 PLUS \nLata 750gr \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0490', 'Freon M049 PLUS \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0496', 'Freon M049 PLUS \nLata 750gr ', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0495', 'Freon M049 PLUS \nLata 750gr \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0490', 'Freon M049 PLUS \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0496', 'Freon M049 PLUS \nLata 750gr ', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product (name, description, quantity, price, order_id, main_image)
VALUES ('Gas refrigerante M0495', 'Freon M049 PLUS \nLata 750gr \nLata 750gr', 5, 3000.00, 1,'../product-2.jpeg');

INSERT INTO product_images (product_id, image)
VALUES (1, '../product-1.jpeg'),
       (1, '../product-1.jpeg'),
       (2, '../product-2.jpeg'),
       (2, '../product-2.jpeg'),
       (3, '../product-1.jpeg'),
       (3, '../product-1.jpeg'),
       (4, '../product-1.jpeg'),
       (4, '../product-1.jpeg');






