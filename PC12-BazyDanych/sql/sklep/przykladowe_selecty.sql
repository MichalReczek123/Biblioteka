SELECT * FROM products ORDER BY product_id;

SELECT * FROM orders JOIN customers USING(customer_email) ORDER BY customer_email, order_id;

-- producty, które są w konkretnym zamówieniu
SELECT * FROM order_products LEFT JOIN products USING(product_id) WHERE order_id = 1;
