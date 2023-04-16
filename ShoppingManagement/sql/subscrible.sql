/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 22:53:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for subscrible
-- ----------------------------
DROP TABLE IF EXISTS `subscrible`;
CREATE TABLE `subscrible` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '订阅的用户id',
  `shop_id` int DEFAULT NULL COMMENT '订阅的店铺id',
  PRIMARY KEY (`id`),
  KEY `fk_sub_user_id` (`user_id`),
  KEY `fk_sub_shop_id` (`shop_id`),
  CONSTRAINT `fk_sub_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `fk_sub_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
