/*
 Navicat Premium Data Transfer

 Source Server         : famiao_local_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : saleorder02

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 11/09/2020 09:12:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_order_1
-- ----------------------------
DROP TABLE IF EXISTS `f_order_1`;
CREATE TABLE `f_order_1`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_10
-- ----------------------------
DROP TABLE IF EXISTS `f_order_10`;
CREATE TABLE `f_order_10`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_2
-- ----------------------------
DROP TABLE IF EXISTS `f_order_2`;
CREATE TABLE `f_order_2`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_3
-- ----------------------------
DROP TABLE IF EXISTS `f_order_3`;
CREATE TABLE `f_order_3`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_4
-- ----------------------------
DROP TABLE IF EXISTS `f_order_4`;
CREATE TABLE `f_order_4`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_5
-- ----------------------------
DROP TABLE IF EXISTS `f_order_5`;
CREATE TABLE `f_order_5`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_6
-- ----------------------------
DROP TABLE IF EXISTS `f_order_6`;
CREATE TABLE `f_order_6`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_7
-- ----------------------------
DROP TABLE IF EXISTS `f_order_7`;
CREATE TABLE `f_order_7`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_8
-- ----------------------------
DROP TABLE IF EXISTS `f_order_8`;
CREATE TABLE `f_order_8`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_9
-- ----------------------------
DROP TABLE IF EXISTS `f_order_9`;
CREATE TABLE `f_order_9`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_amount` decimal(10, 2) NULL DEFAULT NULL,
  `hongbao_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_1
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_1`;
CREATE TABLE `f_order_item_1`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_10
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_10`;
CREATE TABLE `f_order_item_10`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_2
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_2`;
CREATE TABLE `f_order_item_2`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_3
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_3`;
CREATE TABLE `f_order_item_3`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_4
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_4`;
CREATE TABLE `f_order_item_4`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_5
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_5`;
CREATE TABLE `f_order_item_5`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_6
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_6`;
CREATE TABLE `f_order_item_6`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_7
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_7`;
CREATE TABLE `f_order_item_7`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_8
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_8`;
CREATE TABLE `f_order_item_8`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for f_order_item_9
-- ----------------------------
DROP TABLE IF EXISTS `f_order_item_9`;
CREATE TABLE `f_order_item_9`  (
  `uid` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `seller_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `food_id` bigint(20) NULL DEFAULT NULL,
  `food_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `order_item_amount` decimal(10, 2) NULL DEFAULT NULL,
  `order_item_sum` int(255) NULL DEFAULT NULL,
  `promotion_id` bigint(20) NULL DEFAULT NULL,
  `promotion_amount` decimal(10, 2) NULL DEFAULT NULL,
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `coupon_amount` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_3
-- ----------------------------
DROP TABLE IF EXISTS `t_order_3`;
CREATE TABLE `t_order_3`  (
  `orderId` bigint(20) NOT NULL,
  `goodsName` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_4
-- ----------------------------
DROP TABLE IF EXISTS `t_order_4`;
CREATE TABLE `t_order_4`  (
  `orderId` bigint(20) NOT NULL,
  `goodsName` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
