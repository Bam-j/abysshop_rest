DELIMITER $$
CREATE PROCEDURE insert_order_product_dummy_data()
BEGIN
  START TRANSACTION;

  INSERT INTO order_products (user_id, product_id, cart_id, order_id)
    VALUES (1, 1, 1, 1),
           (1, 1, 2, 2),
           (1, 2, 2, 2),
           (2, 1, 3, 3),
           (3, 1, 4, 4),
           (4, 1, 5, 5),
           (5, 1, 6, 6),
           (5, 1, 7, 7),
           (5, 2, 7, 7),
           (5, 1, 8, 8),
           (5, 2, 8, 8),
           (5, 3, 8, 8),
           (6, 1, 9, 9),
           (7, 1, 10, 10);

  COMMIT;
END $$
DELIMITER ;

CALL insert_order_product_dummy_data();
