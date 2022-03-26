INSERT INTO products(product_id, product_name, price, vat, description)
	VALUES (1, 'pralka', 2900.00, 0.23, 'Pralka szybkoobrotowa');

INSERT INTO products(product_id, product_name, price, vat, description)
	VALUES (2, 'odkurzacz', 800.00, 0.23, 'Odkurzacz automatyczny');

INSERT INTO products(product_id, product_name, price, vat, description)
	VALUES (3, 'telewizor 55"', 3300.00, 0.23, 'Telewizor 55 cali 4K');

INSERT INTO products(product_id, product_name, price, vat, description)
	VALUES (4, 'telewizor 40"', 2200.00, 0.23, 'Telewizor 40 Full HD');

INSERT INTO products(product_id, product_name, price, vat)
	VALUES (5, 'myszka gejmerska', 444.00, 0.23);

	
INSERT INTO customers(customer_email, phone, customer_name, address, postal_code, city)
	VALUES ('ala@example.com', '123123123', 'Ala Kowalska', 'Jasna 14/16', '01-234', 'Warszawa');

INSERT INTO customers(customer_email, phone, customer_name, address, postal_code, city)
	VALUES ('ola@example.com', '321321321', 'Ola Malinowska', 'Ciemna 133', '99-999', 'Pcim');


INSERT INTO orders(order_id, customer_email, order_date, order_status)
	VALUES (1, 'ala@example.com', '2021-11-20 12:30:00', 'paid');

INSERT INTO orders(order_id, customer_email, order_date, order_status)
	VALUES (2, 'ola@example.com', '2021-11-18 10:00:00', 'shipped');

INSERT INTO orders(order_id, customer_email)
	VALUES (3, 'ala@example.com');


INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (1, 1, 1, 2900.00, 0.23);
INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (1, 2, 3, 2400.00, 0.23);
INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (2, 2, 1, 800.00, 0.23);
INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (3, 4, 1, 2200.00, 0.23);
INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (3, 3, 1, 300.00, 0.23);
INSERT INTO order_products(order_id, product_id, quantity, actual_price, actual_vat) 
	VALUES (3, 5, 1, 1000.00, 0.23);
	
-- SELECT setval('orders_seq', 11, true);
