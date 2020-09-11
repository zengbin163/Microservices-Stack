/*
 Navicat Premium Data Transfer

 Source Server         : famiao_local_mongo
 Source Server Type    : MongoDB
 Source Server Version : 40400
 Source Host           : localhost:27017
 Source Schema         : coupon

 Target Server Type    : MongoDB
 Target Server Version : 40400
 File Encoding         : 65001

 Date: 11/09/2020 09:31:14
*/


// ----------------------------
// Collection structure for user_coupon
// ----------------------------
db.getCollection("user_coupon").drop();
db.createCollection("user_coupon");
