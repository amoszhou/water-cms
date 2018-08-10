/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : water

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-10 18:18:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_archive
-- ----------------------------
DROP TABLE IF EXISTS `t_archive`;
CREATE TABLE `t_archive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `hall_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `record_user` int(11) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `factory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_archive
-- ----------------------------
INSERT INTO `t_archive` VALUES ('1', '1', '1', '2018-07-04 11:50:08', '1', '1', '1', '0', '3');
INSERT INTO `t_archive` VALUES ('3', '5', '22', '2018-07-09 15:31:19', '2', 'BIAOCE1', '1', '0', '3');
INSERT INTO `t_archive` VALUES ('4', '1', 'jdaijsd123912389', '2018-08-01 16:41:17', '1', '表册2222', '1', '0', '4');
INSERT INTO `t_archive` VALUES ('5', '7', '21', '2018-08-02 17:21:37', '2', '管理员2的表册', '1', '0', '2');
INSERT INTO `t_archive` VALUES ('6', '1', '123code', '2018-08-06 10:03:50', '1', '表册2', '2', '0', '4');

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '片区编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人ID',
  `hall_id` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL COMMENT '片区名',
  `factory_id` int(11) NOT NULL,
  `create_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `factory_id` (`factory_id`),
  CONSTRAINT `t_area_ibfk_1` FOREIGN KEY (`factory_id`) REFERENCES `t_factory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_area
-- ----------------------------
INSERT INTO `t_area` VALUES ('1', '21', '2018-06-27 21:44:53', '21', '2', '0', '片区1', '4', '132132');
INSERT INTO `t_area` VALUES ('5', '2', '2018-07-04 09:44:47', '10', null, '0', '311321', '1', '45646546');
INSERT INTO `t_area` VALUES ('6', '3', '2018-07-04 10:25:24', '1', null, '1', '片区3', '2', 'LJD');
INSERT INTO `t_area` VALUES ('7', '78', '2018-08-02 17:21:13', '1', null, '0', '管理员2的片区', '2', 'LJD');
INSERT INTO `t_area` VALUES ('8', '23', '2018-08-07 11:37:39', '1', null, '0', '324', '1', 'LJD');

-- ----------------------------
-- Table structure for t_bizhall
-- ----------------------------
DROP TABLE IF EXISTS `t_bizhall`;
CREATE TABLE `t_bizhall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `factory_id` int(11) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `factory_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_bizhall
-- ----------------------------
INSERT INTO `t_bizhall` VALUES ('1', '1', '4', '0', '营业厅1', '1', '水厂12321');
INSERT INTO `t_bizhall` VALUES ('2', '1', '2', '0', '1', '1', '322132131');
INSERT INTO `t_bizhall` VALUES ('3', '2', null, '1', '2', 'wwwwwwwwwwwwwwwwwww', '2');
INSERT INTO `t_bizhall` VALUES ('4', '313', '1', '0', '2312', '3213333333333333333333', '水厂1');
INSERT INTO `t_bizhall` VALUES ('5', '73284732849732', '1', '0', '372847', '233', '水厂1');
INSERT INTO `t_bizhall` VALUES ('7', '东莞团贷网3', '4', '0', '8——2营业厅', '1235684521', '水厂3');
INSERT INTO `t_bizhall` VALUES ('8', '3213', '6', '1', '12312', '3213', null);
INSERT INTO `t_bizhall` VALUES ('9', '撒旦教啊', '1', '0', '8/7营业厅', '1231', null);

-- ----------------------------
-- Table structure for t_charge_record
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_record`;
CREATE TABLE `t_charge_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(65,5) DEFAULT NULL COMMENT '金额',
  `charge_type` int(11) DEFAULT NULL COMMENT '消费类型（1--充值，2--缴费）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `cust_code` varchar(255) DEFAULT NULL COMMENT '消费编码',
  `cust_id` int(11) DEFAULT NULL COMMENT '消费人ID',
  `invoice_code` varchar(255) DEFAULT NULL COMMENT '发票编码',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付方式(1支付宝，2微信，3现金,4账号余额，5充值类型，无支付类型)',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `factory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_charge_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `archive_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` int(11) DEFAULT NULL,
  `factory_id` int(11) DEFAULT NULL,
  `family_count` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `hall_id` int(11) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueCustomerCode` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=20365 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_customer
-- ----------------------------

-- ----------------------------
-- Table structure for t_customer_account
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_account`;
CREATE TABLE `t_customer_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(65,5) DEFAULT '0.00000' COMMENT '余额',
  `cust_id` int(11) DEFAULT NULL COMMENT '顾客ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` int(11) DEFAULT NULL COMMENT '操作人',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `factory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20325 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_customer_account
-- ----------------------------

-- ----------------------------
-- Table structure for t_customer_meter
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_meter`;
CREATE TABLE `t_customer_meter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `cust_code` varchar(255) DEFAULT NULL,
  `cust_id` int(11) NOT NULL,
  `enable_date` timestamp NULL DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  `price_type` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `meter_id` int(11) NOT NULL,
  `factory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrxpolxsgmy5693hdxcmytp6x` (`price_type`),
  CONSTRAINT `FKqrxpolxsgmy5693hdxcmytp6x` FOREIGN KEY (`price_type`) REFERENCES `t_price_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20324 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_customer_meter
-- ----------------------------

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `factory_id` int(11) DEFAULT '-1' COMMENT '水厂ID（-1为尚未分配水厂）',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `tel_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `user_type` int(11) NOT NULL DEFAULT '1',
  `factory_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unphone` (`tel_phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('1', null, '123', 'LJD', '123', 'ljd的userName', '0', '0', '21312');
INSERT INTO `t_employee` VALUES ('2', '1', '123', '用户1的真实姓名', '321', '用户123', '0', '1', '水厂3');
INSERT INTO `t_employee` VALUES ('3', '8', '1', '1', '21534156', '1', '0', '1', '水厂1');
INSERT INTO `t_employee` VALUES ('4', '1', '3123', '管理员2', '312312312', '2', '0', '1', '123213');
INSERT INTO `t_employee` VALUES ('5', '1', '123', '管理员1', '34534', '435', '0', '1', '水厂1');
INSERT INTO `t_employee` VALUES ('6', '2', '21', '21', '212222', '12', '0', '1', '水厂3');
INSERT INTO `t_employee` VALUES ('7', '2', '8', '80', '80', '8', '0', '1', '322');

-- ----------------------------
-- Table structure for t_factory
-- ----------------------------
DROP TABLE IF EXISTS `t_factory`;
CREATE TABLE `t_factory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '水厂ID',
  `address` varchar(255) DEFAULT NULL COMMENT '水厂地址',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '管理员',
  `name` varchar(255) DEFAULT NULL COMMENT '水厂名',
  `tel` varchar(255) DEFAULT NULL COMMENT '水厂电话',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_factory
-- ----------------------------
INSERT INTO `t_factory` VALUES ('1', '地址1', '管理员12', '水厂1', '13521215445', '0', '5');
INSERT INTO `t_factory` VALUES ('2', '33', '33', '322', '15622936804', '0', '2');
INSERT INTO `t_factory` VALUES ('4', '东莞', '管理上大街上的', '水厂3', '12456789', '0', '2');
INSERT INTO `t_factory` VALUES ('5', '4545', '312', '454', '1213', '0', '4');
INSERT INTO `t_factory` VALUES ('6', '43242', '3453', '2332', '232', '0', '5');
INSERT INTO `t_factory` VALUES ('7', '322132', null, '123213', '213213', '0', '4');
INSERT INTO `t_factory` VALUES ('8', '新水厂8', null, '新水厂8', '123', '0', null);

-- ----------------------------
-- Table structure for t_meter
-- ----------------------------
DROP TABLE IF EXISTS `t_meter`;
CREATE TABLE `t_meter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `model` varchar(15) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `specification` varchar(10) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_meter
-- ----------------------------
INSERT INTO `t_meter` VALUES ('1', '12', '2018-07-11 14:49:59', '3', '水表1', '3', '266', '0');
INSERT INTO `t_meter` VALUES ('2', '454', '2018-07-11 15:00:30', '545', '水表2', '4540', '4545', '0');

-- ----------------------------
-- Table structure for t_pay_record
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_record`;
CREATE TABLE `t_pay_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `water_record_id` int(11) DEFAULT NULL COMMENT '用水记录Id',
  `pay_state` int(11) DEFAULT '-1' COMMENT '缴费状态（-1为未缴费，1为缴费）',
  `customer_id` int(11) DEFAULT NULL COMMENT '消费者id',
  `water_begin_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '产生水费开始时间',
  `water_end_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '产生水费结束时间',
  `pay_type` int(11) DEFAULT '-1' COMMENT '支付方式(1支付宝，2微信，3现金,4账号余额,-1暂未缴费)',
  `water_fee` decimal(65,5) DEFAULT NULL COMMENT '正常水费',
  `sewage_fee` decimal(65,5) DEFAULT '0.00000' COMMENT '污水费',
  `late_fee` decimal(65,5) DEFAULT NULL COMMENT '滞纳金',
  `total_fee` decimal(65,5) DEFAULT NULL COMMENT '汇总费用',
  `water_record_code` varchar(255) DEFAULT NULL,
  `factory_id` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_pay_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_price_type
-- ----------------------------
DROP TABLE IF EXISTS `t_price_type`;
CREATE TABLE `t_price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `factory_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(65,5) DEFAULT NULL,
  `sewage` decimal(65,5) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT '0' COMMENT '价格类型(0生活用水，1工业用水)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `factoryAndType` (`factory_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_price_type
-- ----------------------------
INSERT INTO `t_price_type` VALUES ('1', '1', '价格1', '11.69000', '12.00000', '0', '0');
INSERT INTO `t_price_type` VALUES ('2', '4', '水厂3的价格', '10.20000', '6.50000', '0', '0');
INSERT INTO `t_price_type` VALUES ('3', '2', '112', '1.00000', '1.00000', '0', '1');
INSERT INTO `t_price_type` VALUES ('4', '8', '1', '10.00000', '10.00000', '0', '0');

-- ----------------------------
-- Table structure for t_water_record
-- ----------------------------
DROP TABLE IF EXISTS `t_water_record`;
CREATE TABLE `t_water_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `charge_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` int(11) DEFAULT NULL,
  `curr_number` decimal(65,5) DEFAULT NULL,
  `cust_code` varchar(255) DEFAULT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `fee` decimal(65,5) DEFAULT NULL,
  `last_number` decimal(65,5) DEFAULT NULL,
  `meter_id` int(11) DEFAULT NULL,
  `pay` int(11) DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` int(11) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  `code` varchar(255) DEFAULT NULL,
  `factory_id` int(11) DEFAULT NULL,
  `water_record_begin_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用水记录开始时间',
  `water_record_end_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用水记录结束时间',
  PRIMARY KEY (`id`),
  KEY `FKr0eonf22lvb8ffi19vf6q79oy` (`cust_id`),
  CONSTRAINT `FKr0eonf22lvb8ffi19vf6q79oy` FOREIGN KEY (`cust_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_water_record
-- ----------------------------
