/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : take-out

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-09-28 02:11:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` int(3) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `customer_sex` int(3) NOT NULL,
  `customer_address` varchar(200) COLLATE utf8_bin NOT NULL,
  `customer_phone` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'wj', '1', '武昌首义学院西八', '13554638');
INSERT INTO `customer` VALUES ('2', 'wzx', '0', '武昌首义学院西七', '15684654');
INSERT INTO `customer` VALUES ('3', 'jzy', '0', '武昌首义学院西六', '13854654');
INSERT INTO `customer` VALUES ('4', 'yzj', '1', '武昌首义学院西五', '13645265');
INSERT INTO `customer` VALUES ('5', 'zyn', '1', '武昌首义学院南四', '13546854');
INSERT INTO `customer` VALUES ('6', 'yzc', '1', '武昌首义学院中十二', '14569856');
INSERT INTO `customer` VALUES ('7', 'lc', '1', '武昌首义学院南六', '16546854');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(3) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `employee_sex` int(3) NOT NULL,
  `employee_age` int(3) NOT NULL,
  `employee_phone` int(12) NOT NULL,
  `employee_role` int(3) NOT NULL,
  `employee_state` int(3) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'wj', '1', '22', '1232546856', '2', '0');
INSERT INTO `employee` VALUES ('2', 'jzy', '0', '22', '1238546859', '1', '1');
INSERT INTO `employee` VALUES ('3', 'wzx', '0', '23', '1238546952', '0', '0');
INSERT INTO `employee` VALUES ('4', 'zbc', '0', '30', '1355463838', '0', '1');
INSERT INTO `employee` VALUES ('5', 'wyf', '1', '32', '1354655466', '0', '0');
INSERT INTO `employee` VALUES ('6', 'cxx', '1', '23', '1526545266', '0', '1');
INSERT INTO `employee` VALUES ('7', 'yzj', '1', '21', '1256458652', '0', '0');
INSERT INTO `employee` VALUES ('8', 'zyn', '1', '21', '1352684656', '1', '0');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(3) NOT NULL AUTO_INCREMENT,
  `notice_text` varchar(200) COLLATE utf8_bin NOT NULL,
  `notice_state` int(3) NOT NULL,
  `notice_time` datetime NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('4', '今日新增菜品 糖醋里脊!!!', '1', '2020-09-27 17:08:27');
INSERT INTO `notice` VALUES ('5', '特别提醒！今日22:00暂停营业', '0', '2020-09-25 10:52:57');
INSERT INTO `notice` VALUES ('6', '国庆活动！满30送10元红包', '0', '2020-10-28 00:00:00');
INSERT INTO `notice` VALUES ('11', '店周年庆!下单满20送一份荤菜', '2', '2020-09-22 21:13:03');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(3) NOT NULL AUTO_INCREMENT,
  `order_no` int(20) NOT NULL,
  `order_time` datetime NOT NULL,
  `order_state` int(3) NOT NULL,
  `customer_id` int(3) NOT NULL,
  `shop_id` int(3) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('4', '8653655', '2020-09-24 20:20:49', '1', '1', '4');
INSERT INTO `orders` VALUES ('6', '5464645', '2020-09-05 11:21:47', '1', '3', '9');
INSERT INTO `orders` VALUES ('7', '7465465', '2020-09-25 14:24:55', '3', '2', '9');
INSERT INTO `orders` VALUES ('8', '5445454', '2020-09-21 07:08:44', '3', '3', '5');
INSERT INTO `orders` VALUES ('9', '6565456', '2020-09-26 14:43:09', '0', '3', '8');
INSERT INTO `orders` VALUES ('10', '5464565', '2020-09-26 15:22:08', '1', '1', '5');
INSERT INTO `orders` VALUES ('11', '5546464', '2020-09-27 10:34:33', '3', '2', '10');
INSERT INTO `orders` VALUES ('12', '4586523', '2020-09-22 15:04:08', '3', '2', '11');
INSERT INTO `orders` VALUES ('13', '1548754', '2020-09-21 10:31:43', '3', '2', '10');
INSERT INTO `orders` VALUES ('14', '6589564', '2020-09-22 22:37:02', '3', '3', '4');
INSERT INTO `orders` VALUES ('15', '9654251', '2020-09-22 19:31:44', '3', '6', '10');
INSERT INTO `orders` VALUES ('16', '5326542', '2020-09-22 14:43:21', '3', '5', '9');
INSERT INTO `orders` VALUES ('17', '5658452', '2020-09-22 11:29:45', '3', '4', '8');
INSERT INTO `orders` VALUES ('18', '6523541', '2020-09-23 23:02:28', '3', '7', '4');
INSERT INTO `orders` VALUES ('19', '5426532', '2020-09-23 18:45:11', '3', '5', '5');
INSERT INTO `orders` VALUES ('20', '6586542', '2020-09-23 19:45:58', '3', '6', '9');
INSERT INTO `orders` VALUES ('21', '6542856', '2020-09-23 21:47:26', '3', '6', '10');
INSERT INTO `orders` VALUES ('22', '8564985', '2020-09-24 17:48:54', '3', '1', '10');
INSERT INTO `orders` VALUES ('37', '6555645', '2020-09-22 20:44:26', '3', '2', '5');
INSERT INTO `orders` VALUES ('38', '6556654', '2020-09-23 21:47:26', '3', '2', '5');
INSERT INTO `orders` VALUES ('39', '6465454', '2020-09-23 21:47:26', '3', '2', '5');
INSERT INTO `orders` VALUES ('43', '2312312', '2020-09-24 17:48:54', '3', '3', '9');
INSERT INTO `orders` VALUES ('44', '5554645', '2020-09-24 17:48:54', '3', '4', '5');
INSERT INTO `orders` VALUES ('45', '2321321', '2020-09-24 17:48:54', '3', '2', '9');
INSERT INTO `orders` VALUES ('47', '3333223', '2020-09-24 17:48:54', '3', '2', '10');
INSERT INTO `orders` VALUES ('48', '4442321', '2020-09-24 17:48:54', '3', '2', '9');
INSERT INTO `orders` VALUES ('49', '2321332', '2020-09-25 20:48:26', '3', '2', '8');
INSERT INTO `orders` VALUES ('50', '3242144', '2020-09-25 20:48:26', '3', '2', '4');
INSERT INTO `orders` VALUES ('51', '2321321', '2020-09-25 20:48:26', '3', '2', '5');
INSERT INTO `orders` VALUES ('52', '4552423', '2020-09-25 20:48:26', '3', '2', '8');
INSERT INTO `orders` VALUES ('53', '3232133', '2020-09-25 20:48:26', '3', '5', '9');
INSERT INTO `orders` VALUES ('54', '5533244', '2020-09-25 20:48:26', '3', '2', '11');
INSERT INTO `orders` VALUES ('55', '3213213', '2020-09-26 20:49:50', '3', '2', '10');
INSERT INTO `orders` VALUES ('56', '4444544', '2020-09-26 20:49:50', '3', '7', '8');
INSERT INTO `orders` VALUES ('57', '4324324', '2020-09-26 20:49:50', '3', '6', '8');
INSERT INTO `orders` VALUES ('58', '5453454', '2020-09-26 20:49:50', '3', '1', '11');
INSERT INTO `orders` VALUES ('59', '7777655', '2020-09-26 20:49:50', '3', '3', '9');
INSERT INTO `orders` VALUES ('60', '2132133', '2020-09-26 20:49:50', '3', '2', '10');
INSERT INTO `orders` VALUES ('61', '3213123', '2020-09-27 20:51:20', '3', '6', '10');
INSERT INTO `orders` VALUES ('62', '5534234', '2020-09-27 20:51:20', '3', '4', '9');
INSERT INTO `orders` VALUES ('63', '3213213', '2020-09-27 20:51:20', '3', '6', '5');
INSERT INTO `orders` VALUES ('64', '3213213', '2020-09-27 20:51:20', '3', '5', '8');
INSERT INTO `orders` VALUES ('65', '2321312', '2020-09-27 20:51:20', '3', '1', '4');
INSERT INTO `orders` VALUES ('66', '4214213', '2020-09-27 20:51:20', '3', '2', '9');
INSERT INTO `orders` VALUES ('67', '5486523', '2020-09-28 02:03:23', '2', '7', '4');
INSERT INTO `orders` VALUES ('68', '2365845', '2020-09-28 09:03:56', '1', '1', '8');
INSERT INTO `orders` VALUES ('69', '6452862', '2020-09-28 08:04:38', '3', '5', '11');

-- ----------------------------
-- Table structure for `shops`
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `shop_id` int(3) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shop_price` double NOT NULL,
  `shop_info` varchar(200) COLLATE utf8_bin NOT NULL,
  `shop_state` int(2) NOT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES ('4', '红烧排骨', '38', '好吃', '0');
INSERT INTO `shops` VALUES ('5', '水煮肉片', '22', '真香', '1');
INSERT INTO `shops` VALUES ('8', '干煸土豆丝', '18', '硬邦邦', '0');
INSERT INTO `shops` VALUES ('9', '青椒肉丝', '13', '炒肉', '1');
INSERT INTO `shops` VALUES ('10', '鱼香肉丝', '23', '鱼香肉丝', '1');
INSERT INTO `shops` VALUES ('11', '肉末茄子', '18', '肉末茄子', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(3) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `employee_id` int(3) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wj', '123456', '1');
INSERT INTO `user` VALUES ('2', 'wzx', '123456', '3');
INSERT INTO `user` VALUES ('3', 'jzy', '123456', '2');
