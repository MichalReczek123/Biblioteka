DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    -- kolumna typ opcje,
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    -- maksymalna wartosc to 99999999,99
    price NUMERIC(10, 2) NOT NULL CHECK (price > 0),
    vat NUMERIC(2, 2) CHECK (vat >= 0),
    description TEXT
);

CREATE TABLE customers (
    customer_email VARCHAR(50) PRIMARY KEY CHECK (customer_email LIKE '%@%.%'),
    customer_name VARCHAR(100) NOT NULL CHECK (length(customer_name) >= 3),
    phone VARCHAR(13),
    address VARCHAR(100),
    postal_code CHAR(6),
    city VARCHAR(50)
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_email VARCHAR(50) NOT NULL REFERENCES customers(customer_email),
    order_date TIMESTAMP NOT NULL,
    order_status VARCHAR(10) NOT NULL CHECK (order_status IN ('unpaid', 'paid', 'shipped', 'closed'))
);

CREATE TABLE order_products (
    order_id INTEGER NOT NULL REFERENCES orders(order_id),
    product_id INTEGER NOT NULL REFERENCES products(product_id),
    quantity SMALLINT NOT NULL CHECK (quantity > 0),
    actual_price NUMERIC(10, 2) NOT NULL,
    actual_vat NUMERIC(2, 2),
    
    PRIMARY KEY(order_id, product_id)
);
