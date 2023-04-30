/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-04-30 21:01:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `time` bigint DEFAULT NULL COMMENT '交易时间的时间戳',
  `send_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发货地址',
  `receive_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货地址',
  `goods_id` bigint DEFAULT NULL COMMENT '商品id',
  `shop_id` int DEFAULT NULL COMMENT '店铺id',
  `user_id` int DEFAULT NULL COMMENT '消费者id',
  `status` int DEFAULT NULL COMMENT '订单状态1未发货 2已发货 3已收货 4退款中 5已退款 6退款失败',
  `number` int DEFAULT NULL COMMENT '数量',
  `single_price` int DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`),
  KEY `fk_order_good_id` (`goods_id`),
  KEY `fk_order_user_id` (`user_id`),
  CONSTRAINT `fk_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
