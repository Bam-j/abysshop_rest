DELETE FROM order_products;
DELETE FROM carts_table;
DELETE FROM orders_table;
DELETE FROM products_table;
DELETE FROM product_image_table;
DELETE FROM users_table;
DELETE FROM point_recharge_table;
DELETE FROM point_recharge_detail_table;

ALTER TABLE order_products AUTO_INCREMENT = 1;
ALTER TABLE carts_table AUTO_INCREMENT = 1;
ALTER TABLE orders_table AUTO_INCREMENT = 1;
ALTER TABLE products_table AUTO_INCREMENT = 1;
ALTER TABLE product_image_table AUTO_INCREMENT = 1;
ALTER TABLE users_table AUTO_INCREMENT = 1;
ALTER TABLE point_recharge_table AUTO_INCREMENT = 1;
ALTER TABLE point_recharge_detail_table AUTO_INCREMENT = 1;