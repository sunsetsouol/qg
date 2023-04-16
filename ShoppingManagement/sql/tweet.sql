/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 23:02:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tweet
-- ----------------------------
DROP TABLE IF EXISTS `tweet`;
CREATE TABLE `tweet` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_id` int NOT NULL COMMENT '店铺id',
  `tweet` varchar(200) NOT NULL COMMENT '推文',
  PRIMARY KEY (`id`),
  KEY `fk_dynamic_shop_id` (`shop_id`) USING BTREE,
  CONSTRAINT `fk_dynamic_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
