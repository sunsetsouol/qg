/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-28 21:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for consultation
-- ----------------------------
DROP TABLE IF EXISTS `consultation`;
CREATE TABLE `consultation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` bigint NOT NULL COMMENT '商品id',
  `consultation` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '咨询内容',
  `user_id` int DEFAULT NULL COMMENT '咨询者id',
  PRIMARY KEY (`id`,`goods_id`),
  KEY `fk_cst_user_id` (`user_id`),
  KEY `fk_cst_g_id` (`goods_id`),
  CONSTRAINT `fk_cst_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
