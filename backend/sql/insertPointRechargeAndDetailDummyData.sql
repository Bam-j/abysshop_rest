DELIMITER $$
CREATE PROCEDURE insert_point_recharge_and_detail_dummy_data()
BEGIN
  START TRANSACTION;

  INSERT INTO point_recharge_table (user_id, nickname, points)
  VALUES (1, 'user1', 1000),
         (1, 'user1', 2000),
         (2, 'user2', 1000);

  INSERT INTO point_recharge_detail_table (recharge_id, user_id, bank, account_number)
    VALUES (1, 1, '까까오', '000-00000-00000'),
           (2, 1, '한나', '00-0000-000000'),
           (3, 2, '군민', '00-00000-00000');

  COMMIT;
END $$
DELIMITER ;

CALL insert_point_recharge_and_detail_dummy_data();
