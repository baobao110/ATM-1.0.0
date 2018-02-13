/*
Navicat MySQL Data Transfer

Source Server         : 333
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : 11

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2017-11-19 12:57:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `number` int(4) NOT NULL,
  `money` double(10,2) NOT NULL,
  `createtime` datetime NOT NULL,
  `modifytime` datetime NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('31', '4067', '3037.00', '2017-11-15 11:49:42', '2017-11-15 12:14:59', 'dfdfdfd', '0');
INSERT INTO `card` VALUES ('32', '4060', '1678.00', '2017-11-15 11:50:53', '2017-11-15 12:14:59', 'dfdfdfd', '0');
INSERT INTO `card` VALUES ('33', '232', '3232.00', '2017-11-16 10:52:31', '2017-11-16 10:52:31', 'dfdfdfd', '0');
INSERT INTO `card` VALUES ('34', '2532', '0.00', '2017-11-16 10:58:12', '2017-11-16 10:58:12', 'dfdfdfd', '0');
INSERT INTO `card` VALUES ('35', '3986', '0.53', '2017-11-16 13:35:34', '2017-11-16 13:35:34', 'dfdfdfd', '0');
INSERT INTO `card` VALUES ('36', '424', '10000.00', '2017-11-16 13:36:32', '2017-11-16 23:26:04', '123456', '0');
INSERT INTO `card` VALUES ('37', '2453', '50.00', '2017-11-16 20:21:38', '2017-11-17 03:03:09', '33', '0');
INSERT INTO `card` VALUES ('38', '8015', '0.00', '2017-11-16 20:22:04', '2017-11-16 20:22:04', 'wwwwww', '0');
INSERT INTO `card` VALUES ('41', '1017', '444.00', '2017-11-16 22:57:00', '2017-11-17 01:04:06', '333', '0');
INSERT INTO `card` VALUES ('42', '7402', '444.00', '2017-11-17 00:43:41', '2017-11-17 00:56:34', '111111', '0');
INSERT INTO `card` VALUES ('43', '5626', '130.00', '2017-11-17 00:44:06', '2017-11-17 12:07:35', '111111', '0');
INSERT INTO `card` VALUES ('44', '6018', '97.00', '2017-11-17 02:22:44', '2017-11-19 12:48:00', '123456', '0');
INSERT INTO `card` VALUES ('45', '4133', '412.00', '2017-11-17 02:58:23', '2017-11-19 12:48:00', '111111', '0');
INSERT INTO `card` VALUES ('47', '300', '200.00', '2017-11-17 10:10:23', '2017-11-17 18:18:14', '111111', '0');
