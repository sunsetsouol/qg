/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-19 14:32:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_id` int DEFAULT NULL COMMENT '店铺id',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品描述',
  `sales` int DEFAULT NULL COMMENT '销量',
  `inventory` int DEFAULT NULL COMMENT '库存',
  `price` varchar(20) DEFAULT NULL COMMENT '价格',
  `picture` varchar(20) DEFAULT NULL COMMENT '图片',
  `name` varchar(20) DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`id`),
  KEY `fk_goods_shop_id` (`shop_id`),
  CONSTRAINT `fk_goods_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
