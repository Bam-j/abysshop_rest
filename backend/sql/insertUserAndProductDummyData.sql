DELIMITER $$
CREATE PROCEDURE insert_user_and_product_dummy_data()
BEGIN
  START TRANSACTION;

  INSERT INTO users_table (username, nickname, password, user_type, points)
  VALUES ('user1', 'user1', '0000', 'user', 10000),
         ('user2', 'user2', '0000', 'user', 20000),
         ('user3', 'user3', '0000', 'user', 10000),
         ('user4', 'user4', '0000', 'user', 20000),
         ('user5', 'user5', '0000', 'user', 10000),
         ('user6', 'user6', '0000', 'user', 20000),
         ('user7', 'user7', '0000', 'user', 10000),
         ('user8', 'user8', '0000', 'user', 20000),
         ('user9', 'user9', '0000', 'user', 10000),
         ('user10', 'user10', '0000', 'user', 20000),
         ('user11', 'user11', '0000', 'user', 10000),
         ('user12', 'user12', '0000', 'user', 20000),
         ('user13', 'user13', '0000', 'user', 10000),
         ('user14', 'user14', '0000', 'user', 20000),
         ('user15', 'user15', '0000', 'user', 10000),
         ('user16', 'user16', '0000', 'user', 20000),
         ('user17', 'user17', '0000', 'user', 10000),
         ('user18', 'user18', '0000', 'user', 20000),
         ('user19', 'user19', '0000', 'user', 10000),
         ('user20', 'user20', '0000', 'user', 20000);

  INSERT INTO products_table (product_name, price, description)
  VALUES ('상품1', 10000, '상품 설명1'),
         ('상품2', 20000, '상품 설명2'),
         ('상품3', 10000, '상품 설명3'),
         ('상품4', 20000, '상품 설명4'),
         ('상품5', 10000, '상품 설명5'),
         ('상품6', 20000, '상품 설명6'),
         ('상품7', 10000, '상품 설명7'),
         ('상품8', 20000, '상품 설명8'),
         ('상품9', 10000, '상품 설명9'),
         ('상품10', 20000, '상품 설명10'),
         ('상품11', 10000, '상품 설명11'),
         ('상품12', 20000, '상품 설명12'),
         ('상품13', 10000, '상품 설명13'),
         ('상품14', 20000, '상품 설명14'),
         ('상품15', 10000, '상품 설명15'),
         ('상품16', 20000, '상품 설명16'),
         ('상품17', 10000, '상품 설명17'),
         ('상품18', 20000, '상품 설명18'),
         ('상품19', 10000, '상품 설명19'),
         ('상품20', 20000, '상품 설명20');
  COMMIT;
END $$
DELIMITER ;

CALL insert_user_and_product_dummy_data();
