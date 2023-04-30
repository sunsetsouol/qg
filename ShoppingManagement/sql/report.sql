/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-30 21:01:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` bigint DEFAULT NULL COMMENT '举报商品',
  `user_id` int DEFAULT NULL COMMENT '举报用户的id',
  `status` int DEFAULT NULL COMMENT '举报状态 0审核中 1举报成功 2举报失败',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `fk_rep_user_id` (`user_id`),
  KEY `fk_report_goods_id` (`goods_id`),
  CONSTRAINT `fk_rep_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
