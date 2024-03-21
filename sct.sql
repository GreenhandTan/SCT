/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : sct

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 25/05/2022 16:15:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'Admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '数学', '小航');
INSERT INTO `course` VALUES (2, '日语', '川岛芳子');
INSERT INTO `course` VALUES (3, '英语', 'Tom');
INSERT INTO `course` VALUES (4, '大学英语', 'Tom');
INSERT INTO `course` VALUES (6, '大学物理', '小芳');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `scId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` decimal(5, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`scId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES (63, '20200018', '1', 33.00);
INSERT INTO `sc` VALUES (64, '20200018', '2', 64.00);
INSERT INTO `sc` VALUES (65, '20200018', '3', 70.00);
INSERT INTO `sc` VALUES (66, '20200018', '4', 90.00);
INSERT INTO `sc` VALUES (67, '20200018', '6', 99.00);
INSERT INTO `sc` VALUES (68, '20200019', '1', 84.00);
INSERT INTO `sc` VALUES (69, '20200019', '2', 59.00);
INSERT INTO `sc` VALUES (70, '20200019', '3', 99.00);
INSERT INTO `sc` VALUES (71, '20200019', '4', 90.00);
INSERT INTO `sc` VALUES (72, '20200020', '1', 63.00);
INSERT INTO `sc` VALUES (73, '20200020', '2', 88.00);
INSERT INTO `sc` VALUES (74, '20200010', '2', 57.00);
INSERT INTO `sc` VALUES (75, '20200010', '4', 90.00);
INSERT INTO `sc` VALUES (76, '20200010', '6', 95.00);
INSERT INTO `sc` VALUES (98, '20200001', '3', 99.00);
INSERT INTO `sc` VALUES (99, '20200001', '4', 99.00);
INSERT INTO `sc` VALUES (100, '20200006', '2', NULL);
INSERT INTO `sc` VALUES (101, '20200006', '3', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stuNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stuPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`stuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三', '20200001', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (2, '离散', '20200002', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (3, '张飞', '20200003', 'e35cf7b66449df565f93c607d5a81d09');
INSERT INTO `student` VALUES (5, '小菲菲', '20200004', 'e35cf7b66449df565f93c607d5a81d09');
INSERT INTO `student` VALUES (6, '小飞机', '20200005', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (7, '泰格', '20200006', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (8, '雄安张', '20200007', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (9, '小猪猪', '20200008', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (10, '诸葛亮', '20200009', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (11, '韩信', '20200010', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (12, '鲁班', '20200011', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (20, '鲁班大师', '20200012', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (21, '张大仙', '20200013', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (22, '张霞', '20200014', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (23, '诸葛亮', '20200015', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (24, '超超', '20200016', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (25, '曹超', '20200017', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (26, '马暴', '20200018', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (27, '马保强', '20200019', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (28, '小宝宝', '20200020', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (29, '钉钉', '20200021', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (30, '啊啊', '20200022', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (31, 'vbb', '20200023', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (32, '坎坎坷坷', '20200024', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (33, '啊啊啊', '20200025', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (35, '哇大大', '20200027', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (36, '八八八', '20200028', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (37, '涛涛涛涛', '20200029', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (38, '酷酷酷', '20200030', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (39, '啦啦啦', '20200031', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (40, '扭扭捏捏', '20200032', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `student` VALUES (41, '小勾八', '20200033', 'e35cf7b66449df565f93c607d5a81d09');
INSERT INTO `student` VALUES (43, 'Test', '20200034', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tId` int(11) NOT NULL AUTO_INCREMENT,
  `tName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`tId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '张叁', '10010001', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `teacher` VALUES (2, '小航', '10010002', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `teacher` VALUES (3, '萧芳芳', '10010003', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `teacher` VALUES (4, '小芳', '10010004', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `teacher` VALUES (5, ' 川岛芳子', '10010005', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `teacher` VALUES (6, 'Tom', '10010006', 'e10adc3949ba59abbe56e057f20f883e');

SET FOREIGN_KEY_CHECKS = 1;
