/*
 Navicat Premium Data Transfer

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3306
 Source Schema         : coursechoose

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 23/07/2024 17:37:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_ccourse
-- ----------------------------
DROP TABLE IF EXISTS `c_ccourse`;
CREATE TABLE `c_ccourse`  (
  `stu_id` int NOT NULL,
  `course_id` int NOT NULL,
  `ccourse_mark` int NULL DEFAULT 0,
  `ccourse_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`, `course_id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  CONSTRAINT `c_ccourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_ccourse_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `c_student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_class
-- ----------------------------
DROP TABLE IF EXISTS `c_class`;
CREATE TABLE `c_class`  (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `class_maxnum` int NOT NULL,
  `grade_id` int NOT NULL,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `grade_id`(`grade_id` ASC) USING BTREE,
  CONSTRAINT `grade_id` FOREIGN KEY (`grade_id`) REFERENCES `c_grade` (`grade_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course`  (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `course_credit` float NOT NULL,
  `course_info` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `course_begin_time` datetime NOT NULL,
  `teach_id` int NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `teach_id`(`teach_id` ASC) USING BTREE,
  CONSTRAINT `c_course_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `c_teacher` (`teach_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_grade
-- ----------------------------
DROP TABLE IF EXISTS `c_grade`;
CREATE TABLE `c_grade`  (
  `grade_id` int NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_log
-- ----------------------------
DROP TABLE IF EXISTS `c_log`;
CREATE TABLE `c_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `log_operate` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `log_time` datetime NULL DEFAULT NULL,
  `login_user` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_student
-- ----------------------------
DROP TABLE IF EXISTS `c_student`;
CREATE TABLE `c_student`  (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `stu_sex` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `class_id` int NOT NULL,
  `stu_password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '1',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23214 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_teacher
-- ----------------------------
DROP TABLE IF EXISTS `c_teacher`;
CREATE TABLE `c_teacher`  (
  `teach_id` int NOT NULL AUTO_INCREMENT,
  `teach_name` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `teach_sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `teach_education` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `teach_password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`teach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_login_ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_login_time` datetime NULL DEFAULT NULL,
  `user_privilege` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_name`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
