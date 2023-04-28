/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-28 21:19:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_id` int DEFAULT NULL COMMENT '店铺id',
  `shop_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '店铺名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `sales` int DEFAULT NULL COMMENT '销量',
  `inventory` int DEFAULT NULL COMMENT '库存',
  `price` int DEFAULT NULL COMMENT '价格',
  `picture` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`),
  KEY `fk_shop_goods_id` (`shop_id`),
  KEY `fk_shop_goods_name` (`shop_name`),
  CONSTRAINT `fk_shop_goods_id` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `fk_shop_goods_name` FOREIGN KEY (`shop_name`) REFERENCES `shop` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
