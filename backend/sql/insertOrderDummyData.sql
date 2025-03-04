DELIMITER $$
CREATE PROCEDURE insert_order_dummy_data()
BEGIN
  START TRANSACTION;

  INSERT INTO orders_table (user_id, order_date, total_points, order_state)
  VALUES (1, CURRENT_DATE, 5000, 'shipping'),
         (1, CURRENT_DATE, 5000, 'shipping'),
         (2, CURRENT_DATE, 5000, 'shipping'),
         (3, CURRENT_DATE, 5000, 'shipping'),
         (4, CURRENT_DATE, 5000, 'shipping'),
         (5, CURRENT_DATE, 5000, 'shipping'),
         (5, CURRENT_DATE, 5000, 'shipping'),
         (5, CURRENT_DATE, 5000, 'shipping'),
         (6, CURRENT_DATE, 5000, 'shipping'),
         (7, CURRENT_DATE, 5000, 'shipping');

  COMMIT;
END $$
DELIMITER ;

CALL insert_order_dummy_data();
