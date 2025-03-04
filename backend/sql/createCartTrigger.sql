DELIMITER //

CREATE TRIGGER insert_cart_after_user_insert
  AFTER INSERT ON users_table
  FOR EACH ROW
BEGIN
INSERT INTO carts_table (user_id)
VALUES (NEW.user_id);
END //

DELIMITER ;