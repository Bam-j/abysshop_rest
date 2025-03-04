CREATE DATABASE db_abysshop;

USE db_abysshop;

CREATE TABLE `users_table`
(
  `user_id`   INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username`  VARCHAR(100)             NOT NULL UNIQUE,
  `nickname`  VARCHAR(100)             NOT NULL UNIQUE,
  `password`  VARCHAR(255)             NOT NULL,
  `user_type` ENUM ('user', 'admin')   NOT NULL DEFAULT 'user',
  `points`    INT UNSIGNED             NOT NULL DEFAULT 0
);

CREATE TABLE `products_table`
(
  `product_id`   INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255)             NOT NULL UNIQUE,
  `price`        INT UNSIGNED             NOT NULL,
  `description`  VARCHAR(255)             NULL
);

CREATE TABLE `product_image_table`
(
  `image_id`           INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `product_id`         INT UNSIGNED             NOT NULL,
  `original_file_name` VARCHAR(255)             NULL
);

CREATE TABLE `carts_table`
(
  `cart_id`      INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id`      INT UNSIGNED             NOT NULL,
  `quantity`     INT UNSIGNED             NOT NULL DEFAULT 0,
  `total_points` INT UNSIGNED             NOT NULL DEFAULT 0
);

CREATE TABLE `orders_table`
(
  `order_id`     INT UNSIGNED PRIMARY KEY                   NOT NULL AUTO_INCREMENT,
  `user_id`      INT UNSIGNED                               NOT NULL,
  `order_date`   DATETIME                                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_points` INT UNSIGNED                               NOT NULL,
  `order_state`  ENUM ('shipping', 'completed', 'refunded') NOT NULL DEFAULT 'shipping'
);

CREATE TABLE `order_products`
(
  `user_id`    INT UNSIGNED NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `cart_id`    INT UNSIGNED NOT NULL,
  `order_id`   INT UNSIGNED NOT NULL
);

CREATE TABLE `point_recharge_table`
(
  `recharge_id`            INT UNSIGNED PRIMARY KEY                                                   NOT NULL AUTO_INCREMENT,
  `user_id`                INT UNSIGNED                                                               NOT NULL,
  `nickname`               VARCHAR(200)                                                               NOT NULL,
  `points`                 INT UNSIGNED                                                               NOT NULL,
  `request_time`           DATETIME                                                                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `recharge_request_state` ENUM ('pending_payment', 'pending_point_deposit', 'completed', 'refunded') NOT NULL DEFAULT 'pending_payment'
);

CREATE TABLE `point_recharge_detail_table`
(
  `recharge_detail_id`     INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `recharge_id`            INT UNSIGNED             NOT NULL,
  `user_id`                INT UNSIGNED             NOT NULL,
  `bank`                   VARCHAR(100)             NULL,
  `account_number`         VARCHAR(255)             NULL,
  `deposit_confirmed_time` DATETIME                 NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `cart_items_table`
(
  `cart_item_id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `cart_id`      INT UNSIGNED             NOT NULL,
  `product_id`   INT UNSIGNED             NOT NULL,
  `product_name` VARCHAR(255)             NULL,
  `price`        INT UNSIGNED             NULL,
  `quantity`     INT UNSIGNED             NOT NULL DEFAULT 1
);

ALTER TABLE `carts_table`
  ADD CONSTRAINT `FK_users_table_TO_carts_table_1` FOREIGN KEY (`user_id`) REFERENCES `users_table` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `orders_table`
  ADD CONSTRAINT `FK_users_table_TO_orders_table_1` FOREIGN KEY (`user_id`) REFERENCES `users_table` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `order_products`
  ADD CONSTRAINT `FK_users_table_TO_order_products_1` FOREIGN KEY (`user_id`) REFERENCES `users_table` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `order_products`
  ADD CONSTRAINT `FK_products_table_TO_order_products_1` FOREIGN KEY (`product_id`) REFERENCES `products_table` (`product_id`) ON DELETE CASCADE;

ALTER TABLE `order_products`
  ADD CONSTRAINT `FK_carts_table_TO_order_products_1` FOREIGN KEY (`cart_id`) REFERENCES `carts_table` (`cart_id`) ON DELETE CASCADE;

ALTER TABLE `order_products`
  ADD CONSTRAINT `FK_orders_table_TO_order_products_1` FOREIGN KEY (`order_id`) REFERENCES `orders_table` (`order_id`) ON DELETE CASCADE;

ALTER TABLE `product_image_table`
  ADD CONSTRAINT `FK_products_table_TO_product_image_table_1` FOREIGN KEY (`product_id`) REFERENCES `products_table` (`product_id`) ON DELETE CASCADE;

ALTER TABLE `point_recharge_table`
  ADD CONSTRAINT `FK_users_table_TO_point_recharge_table_1` FOREIGN KEY (`user_id`) REFERENCES `users_table` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `point_recharge_detail_table`
  ADD CONSTRAINT `FK_point_recharge_table_TO_point_recharge_detail_table_1` FOREIGN KEY (`recharge_id`) REFERENCES `point_recharge_table` (`recharge_id`);

ALTER TABLE `point_recharge_detail_table`
  ADD CONSTRAINT `FK_point_recharge_table_TO_point_recharge_detail_table_2` FOREIGN KEY (`user_id`) REFERENCES `point_recharge_table` (`user_id`) ON DELETE CASCADE;

ALTER TABLE `cart_items_table`
  ADD CONSTRAINT `FK_carts_table_TO_cart_items_table_1` FOREIGN KEY (`cart_id`) REFERENCES `carts_table` (`cart_id`) ON DELETE CASCADE;

ALTER TABLE `cart_items_table`
  ADD CONSTRAINT `FK_products_table_TO_cart_items_table_1` FOREIGN KEY (`product_id`) REFERENCES `products_table` (`product_id`) ON DELETE CASCADE;

# 테스트용 관리자 계정 생성 쿼리
INSERT INTO users_table (username, nickname, password, user_type, points)
VALUES ('admin', 'admin_test', 'admin', 'admin', 100000);