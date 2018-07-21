/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : water

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-21 17:44:41
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) DEFAULT NULL COMMENT '片区编码',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_charge_record
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_record`;
CREATE TABLE `t_charge_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(65,5) DEFAULT NULL COMMENT '金额',
  `charge_type` int(11) DEFAULT NULL COMMENT '消费类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `cust_code` varchar(255) DEFAULT NULL COMMENT '消费编码',
  `cust_id` int(11) DEFAULT NULL COMMENT '消费人ID',
  `invoice_code` varchar(255) DEFAULT NULL COMMENT '发票编码',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付方式',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_customer_account
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_account`;
CREATE TABLE `t_customer_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(65,5) DEFAULT NULL COMMENT '余额',
  `cust_id` int(11) DEFAULT NULL COMMENT '顾客ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` int(11) DEFAULT NULL COMMENT '操作人',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `custUnique` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_customer_meter
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_meter`;
CREATE TABLE `t_customer_meter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `cust_code` varchar(255) DEFAULT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `enable_date` datetime DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  `price_type` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrxpolxsgmy5693hdxcmytp6x` (`price_type`),
  CONSTRAINT `FKqrxpolxsgmy5693hdxcmytp6x` FOREIGN KEY (`price_type`) REFERENCES `t_price_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

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
-- Table structure for t_pay_record
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_record`;
CREATE TABLE `t_pay_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `water_record_id` int(11) DEFAULT NULL COMMENT '用水记录Id',
  `pay_state` int(11) DEFAULT '-1' COMMENT '缴费状态（0为未缴费，1为缴费）',
  `customer_id` int(11) DEFAULT NULL COMMENT '消费者id',
  `water_begin_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '产生水费开始时间',
  `water_end_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '产生水费结束时间',
  `pay_type` int(11) DEFAULT '-1' COMMENT '支付方式(1支付宝，2微信，3现金,-1暂未缴费)',
  `water_fee` decimal(65,5) DEFAULT NULL COMMENT '正常水费',
  `sewage_fee` decimal(65,5) DEFAULT '0.00000' COMMENT '污水费',
  `late_fee` decimal(65,5) DEFAULT NULL COMMENT '滞纳金',
  `total_fee` decimal(65,5) DEFAULT NULL COMMENT '汇总费用',
  `water_record_code` varchar(255) DEFAULT NULL,
  `factory_id` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
