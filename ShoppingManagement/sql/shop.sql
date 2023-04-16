/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 23:01:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `boss_id` int NOT NULL COMMENT '店铺拥有者id',
  `fans` int DEFAULT NULL COMMENT '粉丝数',
  `introductation` varchar(255) DEFAULT NULL,
  `sales` int DEFAULT NULL COMMENT '总销量',
  PRIMARY KEY (`id`),
  KEY `fk_shop_boss_id` (`boss_id`),
  CONSTRAINT `fk_shop_boss_id` FOREIGN KEY (`boss_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
