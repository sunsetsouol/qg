/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-30 21:01:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '申请用户id',
  `status` int DEFAULT NULL COMMENT '申请状态 0 申请中 1申请成功 2申请失败',
  `shop_name` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '店铺介绍',
  PRIMARY KEY (`id`),
  KEY `fk_tos_user_id` (`user_id`),
  CONSTRAINT `fk_tos_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
