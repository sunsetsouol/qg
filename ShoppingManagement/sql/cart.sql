/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 23:01:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `good_id` bigint DEFAULT NULL COMMENT '商品id',
  `number` int DEFAULT NULL COMMENT '商品数量',
  `user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `fk_cart_good_id` (`good_id`),
  KEY `fk_cart_user_id` (`user_id`),
  CONSTRAINT `fk_cart_good_id` FOREIGN KEY (`good_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
