/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 23:01:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for toshop
-- ----------------------------
DROP TABLE IF EXISTS `toshop`;
CREATE TABLE `toshop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '申请用户id',
  `status` int DEFAULT NULL COMMENT '申请状态 0 申请中 1申请成功 2申请失败',
  PRIMARY KEY (`id`),
  KEY `fk_tos_user_id` (`user_id`),
  CONSTRAINT `fk_tos_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
