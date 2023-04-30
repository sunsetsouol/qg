/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-30 21:01:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` bigint DEFAULT NULL COMMENT '商品id',
  `shop_id` int DEFAULT NULL COMMENT '店铺id',
  `number` int DEFAULT NULL COMMENT '商品数量',
  `user_id` int NOT NULL COMMENT '用户id',
  `single_price` int DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`),
  KEY `fk_cart_good_id` (`goods_id`),
  KEY `fk_cart_user_id` (`user_id`),
  CONSTRAINT `fk_cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
