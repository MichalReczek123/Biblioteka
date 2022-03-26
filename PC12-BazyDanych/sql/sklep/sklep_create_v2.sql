DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TYPE IF EXISTS order_status;
DROP SEQUENCE IF EXISTS orders_seq;
DROP SEQUENCE IF EXISTS products_seq;

CREATE SEQUENCE products_seq
	START 10;

CREATE SEQUENCE orders_seq
	START 10;

CREATE TABLE products (
    product_id INTEGER DEFAULT nextval('products_seq'),
    product_name VARCHAR(50) NOT NULL,
    price NUMERIC(10, 2) NOT NULL CHECK (price > 0),
    vat NUMERIC(2, 2) CHECK (vat >= 0),
    description TEXT,
	PRIMARY KEY(product_id)
);

CREATE TABLE customers (
    customer_email VARCHAR(50) CHECK (customer_email LIKE '%@%.%'),
    customer_name VARCHAR(100) NOT NULL CHECK (length(customer_name) >= 3),
    phone VARCHAR(13),
    address VARCHAR(100),
    postal_code CHAR(6),
    city VARCHAR(50),
	PRIMARY KEY(customer_email)
);

CREATE TYPE order_status AS ENUM(
	'unpaid', 'paid', 'shipped', 'closed'
);

CREATE TABLE orders (
    order_id INTEGER DEFAULT nextval('orders_seq'),
    customer_email VARCHAR(50) NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
    order_status order_status NOT NULL DEFAULT 'unpaid',
	
	PRIMARY KEY(order_id),
	FOREIGN KEY(customer_email) REFERENCES customers(customer_email)
);

CREATE TABLE order_products (
    order_id INTEGER NOT NULL ,
    product_id INTEGER NOT NULL,
    quantity SMALLINT NOT NULL CHECK (quantity > 0),
    actual_price NUMERIC(10, 2) NOT NULL,
    actual_vat NUMERIC(2, 2),
    
    PRIMARY KEY(order_id, product_id),
	FOREIGN KEY(order_id) REFERENCES orders(order_id),
	FOREIGN KEY(product_id) REFERENCES products(product_id)
);
