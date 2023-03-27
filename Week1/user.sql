/*
Navicat MySQL Data Transfer

Source Server         : navicat
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : db1

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-03-21 17:25:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `academy` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', 'dsa', 'dsa', 'dsa', '1');
INSERT INTO `user` VALUES ('8', 'qwe', 'qwe', 'qwe', '1');
INSERT INTO `user` VALUES ('9', 'qwe', 'wqe', 'qwe', '1');
INSERT INTO `user` VALUES ('10', 'aaa', 'aaa', 'aaa', '1');
INSERT INTO `user` VALUES ('11', 'ppp', 'ppp', 'ppp', '1');
INSERT INTO `user` VALUES ('13', 'xxxxx', 'xxxxxxx', 'xxxxxxxx', '1');
INSERT INTO `user` VALUES ('16', '23', '22', '23', '1');
INSERT INTO `user` VALUES ('19', '23', '24', '23', '1');
INSERT INTO `user` VALUES ('25', '123', '456', '1', '1');
INSERT INTO `user` VALUES ('27', '123', '46', '1', '1');
INSERT INTO `user` VALUES ('29', 'asd', '11', 'asd', '1');
