/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-16 23:01:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `cause` int DEFAULT NULL COMMENT '退款原因 0无理由 1质量问题',
  `status` int DEFAULT NULL COMMENT '退款状态 0退款中 1退款成功 2退款失败',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`),
  KEY `fk_order_id` (`order_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
