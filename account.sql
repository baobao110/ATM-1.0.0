/*
Navicat MySQL Data Transfer

Source Server         : 333
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : 11

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2017-11-18 17:11:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `number` int(4) NOT NULL,
  `money` double(4,0) NOT NULL,
  `type` varchar(255) NOT NULL,
  `createtime` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6317 DEFAULT CHARSET=utf8;
