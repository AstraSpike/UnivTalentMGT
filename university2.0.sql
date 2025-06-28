/*
 Navicat Premium Data Transfer

 Source Server         : UnivTalentMGT-Aliyun
 Source Server Type    : MySQL
 Source Server Version : 90300 (9.3.0)
 Source Host           : 8.217.163.166:3306
 Source Schema         : university

 Target Server Type    : MySQL
 Target Server Version : 90300 (9.3.0)
 File Encoding         : 65001

 Date: 28/06/2025 11:18:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meeting_operation_logs
-- ----------------------------
DROP TABLE IF EXISTS `meeting_operation_logs`;
CREATE TABLE `meeting_operation_logs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `operation_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `operation_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `meeting_operation_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meeting_operation_logs
-- ----------------------------
INSERT INTO `meeting_operation_logs` VALUES (1, 1, '点击人员条目', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (2, 2, '标签筛选', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (3, 3, '查看详细信息', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (4, 4, '点击人员条目', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (5, 5, '标签筛选', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (6, 6, '查看详细信息', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (7, 7, '点击人员条目', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (8, 8, '标签筛选', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (9, 9, '查看详细信息', '2025-06-14 17:31:15');
INSERT INTO `meeting_operation_logs` VALUES (10, 10, '点击人员条目', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for personnel_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `personnel_basic_info`;
CREATE TABLE `personnel_basic_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` enum('male','female') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `entry_time` date NULL DEFAULT NULL,
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `degree` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `university` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `political_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `native_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `marital_status` enum('single','married','divorced','widowed') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ethnicity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of personnel_basic_info
-- ----------------------------
INSERT INTO `personnel_basic_info` VALUES (1, '张三', 'male', 30, '教师', '讲师', '计算机系', '2020-01-01', '本科', '学士', 'XX大学', '计算机科学与技术', '13800138001', 'zhangsan@example.com', '党员', '北京', 'married', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (2, '李四', 'female', 28, '辅导员', '助教', '数学系', '2021-02-02', '硕士', '硕士', 'YY大学', '数学', '13900139001', 'lisi@example.com', '团员', '上海', 'single', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (3, '王五', 'male', 35, '教授', '教授', '物理系', '2018-03-03', '博士', '博士', 'ZZ大学', '物理', '13700137001', 'wangwu@example.com', '党员', '广州', 'married', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (4, '赵六', 'female', 26, '实验员', '助理实验师', '化学系', '2022-04-04', '本科', '学士', 'AA大学', '化学', '13600136001', 'zhaoliu@example.com', '群众', '深圳', 'single', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (5, '孙七', 'male', 40, '系主任', '教授', '生物系', '2015-05-05', '博士', '博士', 'BB大学', '生物学', '13500135001', 'sunqi@example.com', '党员', '成都', 'married', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (6, '周八', 'female', 27, '行政人员', '科员', '教务处', '2021-06-06', '本科', '学士', 'CC大学', '行政管理', '13400134001', 'zhouba@example.com', '团员', '重庆', 'single', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (7, '吴九', 'male', 32, '副教授', '副教授', '历史系', '2019-07-07', '硕士', '硕士', 'DD大学', '历史学', '13300133001', 'wujiu@example.com', '党员', '杭州', 'married', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (8, '郑十', 'female', 29, '图书管理员', '助理馆员', '图书馆', '2022-08-08', '本科', '学士', 'EE大学', '图书馆学', '13200132001', 'zhengshi@example.com', '群众', '南京', 'single', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (9, '王十一', 'male', 37, '教研室主任', '教授', '英语系', '2017-09-09', '博士', '博士', 'FF大学', '英语语言文学', '13100131001', 'wangshiyi@example.com', '党员', '武汉', 'married', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (10, '李十二', 'female', 25, '辅导员助理', '助教', '哲学系', '2023-10-10', '本科', '学士', 'GG大学', '哲学', '13000130001', 'lishier@example.com', '团员', '西安', 'single', '汉族', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_basic_info` VALUES (11, '张三', 'male', 30, '教师', '讲师', '计算机系', '2020-01-01', '本科', '学士', 'XX大学', '计算机科学与技术', '13800138001', 'zhangsan@example.com', '党员', '北京', 'married', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (12, '李四', 'female', 28, '辅导员', '助教', '数学系', '2021-02-02', '硕士', '硕士', 'YY大学', '数学', '13900139001', 'lisi@example.com', '团员', '上海', 'single', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (13, '王五', 'male', 35, '教授', '教授', '物理系', '2018-03-03', '博士', '博士', 'ZZ大学', '物理', '13700137001', 'wangwu@example.com', '党员', '广州', 'married', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (14, '赵六', 'female', 26, '实验员', '助理实验师', '化学系', '2022-04-04', '本科', '学士', 'AA大学', '化学', '13600136001', 'zhaoliu@example.com', '群众', '深圳', 'single', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (15, '孙七', 'male', 40, '系主任', '教授', '生物系', '2015-05-05', '博士', '博士', 'BB大学', '生物学', '13500135001', 'sunqi@example.com', '党员', '成都', 'married', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (16, '周八', 'female', 27, '行政人员', '科员', '教务处', '2021-06-06', '本科', '学士', 'CC大学', '行政管理', '13400134001', 'zhouba@example.com', '团员', '重庆', 'single', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (17, '吴九', 'male', 32, '副教授', '副教授', '历史系', '2019-07-07', '硕士', '硕士', 'DD大学', '历史学', '13300133001', 'wujiu@example.com', '党员', '杭州', 'married', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (18, '郑十', 'female', 29, '图书管理员', '助理馆员', '图书馆', '2022-08-08', '本科', '学士', 'EE大学', '图书馆学', '13200132001', 'zhengshi@example.com', '群众', '南京', 'single', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (19, '王十一', 'male', 37, '教研室主任', '教授', '英语系', '2017-09-09', '博士', '博士', 'FF大学', '英语语言文学', '13100131001', 'wangshiyi@example.com', '党员', '武汉', 'married', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (20, '李十二', 'female', 25, '辅导员助理', '助教', '哲学系', '2023-10-10', '本科', '学士', 'GG大学', '哲学', '13000130001', 'lishier@example.com', '团员', '西安', 'single', '汉族', '2025-06-14 17:34:01', '2025-06-14 17:34:01');
INSERT INTO `personnel_basic_info` VALUES (21, '张三', 'male', 30, '教师', '讲师', '计算机系', '2020-01-01', '本科', '学士', 'XX大学', '计算机科学与技术', '13800138001', 'zhangsan@example.com', '党员', '北京', 'married', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (22, '李四', 'female', 28, '辅导员', '助教', '数学系', '2021-02-02', '硕士', '硕士', 'YY大学', '数学', '13900139001', 'lisi@example.com', '团员', '上海', 'single', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (23, '王五', 'male', 35, '教授', '教授', '物理系', '2018-03-03', '博士', '博士', 'ZZ大学', '物理', '13700137001', 'wangwu@example.com', '党员', '广州', 'married', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (24, '赵六', 'female', 26, '实验员', '助理实验师', '化学系', '2022-04-04', '本科', '学士', 'AA大学', '化学', '13600136001', 'zhaoliu@example.com', '群众', '深圳', 'single', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (25, '孙七', 'male', 40, '系主任', '教授', '生物系', '2015-05-05', '博士', '博士', 'BB大学', '生物学', '13500135001', 'sunqi@example.com', '党员', '成都', 'married', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (26, '周八', 'female', 27, '行政人员', '科员', '教务处', '2021-06-06', '本科', '学士', 'CC大学', '行政管理', '13400134001', 'zhouba@example.com', '团员', '重庆', 'single', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (27, '吴九', 'male', 32, '副教授', '副教授', '历史系', '2019-07-07', '硕士', '硕士', 'DD大学', '历史学', '13300133001', 'wujiu@example.com', '党员', '杭州', 'married', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (28, '郑十', 'female', 29, '图书管理员', '助理馆员', '图书馆', '2022-08-08', '本科', '学士', 'EE大学', '图书馆学', '13200132001', 'zhengshi@example.com', '群众', '南京', 'single', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (29, '王十一', 'male', 37, '教研室主任', '教授', '英语系', '2017-09-09', '博士', '博士', 'FF大学', '英语语言文学', '13100131001', 'wangshiyi@example.com', '党员', '武汉', 'married', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');
INSERT INTO `personnel_basic_info` VALUES (30, '李十二', 'female', 25, '辅导员助理', '助教', '哲学系', '2023-10-10', '本科', '学士', 'GG大学', '哲学', '13000130001', 'lishier@example.com', '团员', '西安', 'single', '汉族', '2025-06-14 17:34:03', '2025-06-14 17:34:03');

-- ----------------------------
-- Table structure for personnel_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `personnel_detail_info`;
CREATE TABLE `personnel_detail_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `personnel_id` int NOT NULL,
  `id_card_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `emergency_contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `emergency_contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `teaching_courses` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `course_hours` int NULL DEFAULT NULL,
  `teaching_classes` int NULL DEFAULT NULL,
  `teaching_evaluation_score` decimal(5, 2) NULL DEFAULT NULL,
  `teaching_awards` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `teaching_materials` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `research_journals` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `research_papers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `publication_time` date NULL DEFAULT NULL,
  `impact_factor` decimal(5, 2) NULL DEFAULT NULL,
  `research_projects` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `project_numbers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `project_funds` decimal(10, 2) NULL DEFAULT NULL,
  `project_time_period` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `patents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `patent_types` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `patent_authorization_time` date NULL DEFAULT NULL,
  `management_departments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `management_responsibilities` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `management_team_size` int NULL DEFAULT NULL,
  `management_decisions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `training_names` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `training_times` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `training_locations` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `training_contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `training_certificates` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `personnel_detail_info_ibfk_1` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of personnel_detail_info
-- ----------------------------
INSERT INTO `personnel_detail_info` VALUES (1, 1, '123456789012345678', '张父', '13800138002', '北京市朝阳区XX路XX号', '计算机编程基础', 48, 2, 85.50, '优秀教学成果二等奖', '《计算机编程基础教程》2022年出版', '计算机学报', '基于深度学习的图像识别研究', '2022-10-01', 2.50, '基于人工智能的教学系统研究', '2022001', 50000.00, '2022-2024', '一种图像识别算法', '发明专利', '2023-05-01', '', '', 0, '', '计算机编程高级培训', '2023年7月', 'XX培训机构', '深度学习算法讲解', '计算机编程高级培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (2, 2, '234567890123456789', '李母', '13900139002', '上海市浦东新区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '', '', 0, '', '辅导员工作培训', '2023年8月', 'YY大学', '学生心理辅导技巧', '辅导员工作培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (3, 3, '345678901234567890', '王兄', '13700137002', '广州市天河区XX路XX号', '大学物理', 64, 3, 90.00, '优秀教学成果一等奖', '《大学物理教程》2021年出版', '物理学报', '量子力学中的多体问题研究', '2021-12-01', 3.00, '量子计算研究', '2021001', 100000.00, '2021-2023', '一种量子计算算法', '发明专利', '2022-10-01', '', '', 0, '', '物理学前沿研究培训', '2023年6月', 'ZZ大学', '量子计算原理', '物理学前沿研究培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (4, 4, '456789012345678901', '赵姐', '13600136002', '深圳市福田区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '', '', 0, '', '化学实验技能培训', '2023年9月', 'AA大学', '化学实验安全与操作', '化学实验技能培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (5, 5, '567890123456789012', '孙妻', '13500135002', '成都市武侯区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '生物系', '制定教学计划和科研规划', 10, '决定引进新的科研设备', '系主任管理培训', '2023年5月', 'BB大学', '系级管理经验分享', '系主任管理培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (6, 6, '678901234567890123', '周父', '13400134002', '重庆市渝中区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '教务处', '安排教学任务和考试安排', 5, '决定调整课程设置', '行政人员管理培训', '2023年10月', 'CC大学', '行政管理流程优化', '行政人员管理培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (7, 7, '789012345678901234', '吴弟', '13300133002', '杭州市西湖区XX路XX号', '中国历史', 48, 2, 88.00, '优秀教学成果三等奖', '《中国历史教程》2022年出版', '历史研究', '明清时期的经济发展研究', '2022-06-01', 2.00, '中国古代经济史研究', '2022002', 30000.00, '2022-2024', '', '', NULL, '', '', 0, '', '历史学研究方法培训', '2023年7月', 'DD大学', '历史文献研究方法', '历史学研究方法培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (8, 8, '890123456789012345', '郑姐', '13200132002', '南京市鼓楼区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '图书馆', '图书分类和借阅管理', 3, '决定更新图书馆系统', '图书馆管理培训', '2023年8月', 'EE大学', '图书馆信息化管理', '图书馆管理培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (9, 9, '901234567890123456', '王父', '13100131002', '武汉市武昌区XX路XX号', '英语写作', 48, 2, 86.00, '优秀教学成果二等奖', '《英语写作教程》2022年出版', '外语教学与研究', '英语写作教学方法研究', '2022-08-01', 2.20, '英语教学改革研究', '2022003', 40000.00, '2022-2024', '', '', NULL, '英语系教研室', '组织教学研讨和课程设计', 8, '决定引进新的英语教材', '英语教学培训', '2023年6月', 'FF大学', '英语教学新方法', '英语教学培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_detail_info` VALUES (10, 10, '012345678901234567', '李母', '13000130002', '西安市碑林区XX路XX号', '', 0, 0, 0.00, '', '', '', '', NULL, 0.00, '', '', 0.00, '', '', '', NULL, '哲学系', '协助辅导员开展工作', 0, '', '辅导员助理培训', '2023年9月', 'GG大学', '学生管理基础', '辅导员助理培训证书', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for personnel_tags
-- ----------------------------
DROP TABLE IF EXISTS `personnel_tags`;
CREATE TABLE `personnel_tags`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `personnel_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  INDEX `tag_id`(`tag_id` ASC) USING BTREE,
  CONSTRAINT `personnel_tags_ibfk_1` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `personnel_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of personnel_tags
-- ----------------------------
INSERT INTO `personnel_tags` VALUES (1, 1, 1, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (2, 1, 2, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (3, 2, 3, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (4, 3, 1, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (5, 3, 2, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (6, 4, 4, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (7, 5, 5, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (8, 6, 6, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (9, 7, 7, '2025-06-14 17:31:15');
INSERT INTO `personnel_tags` VALUES (10, 8, 8, '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for personnel_training_needs
-- ----------------------------
DROP TABLE IF EXISTS `personnel_training_needs`;
CREATE TABLE `personnel_training_needs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `personnel_id` int NOT NULL,
  `ability_shortcomings` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `recommended_courses` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `personnel_training_needs_ibfk_1` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of personnel_training_needs
-- ----------------------------
INSERT INTO `personnel_training_needs` VALUES (1, 1, '科研项目管理能力不足', '科研项目管理培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (2, 2, '学生心理辅导技巧欠缺', '学生心理辅导培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (3, 3, '量子计算前沿知识不足', '量子计算前沿研究培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (4, 4, '化学实验新技术掌握不够', '化学实验新技术培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (5, 5, '团队管理创新能力不足', '团队管理创新培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (6, 6, '行政管理信息化水平不高', '行政管理信息化培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (7, 7, '历史研究新方法不熟悉', '历史研究新方法培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (8, 8, '图书馆信息化管理能力不足', '图书馆信息化管理培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (9, 9, '英语教学新方法应用不够', '英语教学新方法培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `personnel_training_needs` VALUES (10, 10, '学生管理经验不足', '学生管理基础培训课程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag_type` enum('system_generated','user_defined') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, '优秀教师', 'system_generated', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (2, '科研骨干', 'system_generated', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (3, '新入职员工', 'system_generated', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (4, '资深教师', 'system_generated', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (5, '管理人才', 'system_generated', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (6, '教学能手', 'user_defined', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (7, '学术新星', 'user_defined', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (8, '行政精英', 'user_defined', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (9, '实验达人', 'user_defined', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `tags` VALUES (10, '培训先锋', 'user_defined', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for task_execution_records
-- ----------------------------
DROP TABLE IF EXISTS `task_execution_records`;
CREATE TABLE `task_execution_records`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_order_id` int NOT NULL,
  `execution_progress` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `execution_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_order_id`(`task_order_id` ASC) USING BTREE,
  CONSTRAINT `task_execution_records_ibfk_1` FOREIGN KEY (`task_order_id`) REFERENCES `task_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_execution_records
-- ----------------------------
INSERT INTO `task_execution_records` VALUES (1, 1, '已完成教学计划初稿', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (2, 2, '已确定调研方案', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (3, 3, '科研项目实验正在进行中', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (4, 4, '工作计划框架已完成', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (5, 5, '教学资源配置初步优化', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (6, 6, '培训活动筹备中', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (7, 7, '科研成果整理已完成一部分', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (8, 8, '服务质量提升方案正在制定', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (9, 9, '教学方法改进正在调研中', '2025-06-14 17:31:15');
INSERT INTO `task_execution_records` VALUES (10, 10, '学生管理工作加强措施正在讨论中', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for task_orders
-- ----------------------------
DROP TABLE IF EXISTS `task_orders`;
CREATE TABLE `task_orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `initiator_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `task_requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `task_priority` enum('high','medium','low') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `expected_completion_time` date NULL DEFAULT NULL,
  `task_status` enum('pending','in_progress','completed') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `initiator_id`(`initiator_id` ASC) USING BTREE,
  INDEX `receiver_id`(`receiver_id` ASC) USING BTREE,
  CONSTRAINT `task_orders_ibfk_1` FOREIGN KEY (`initiator_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `task_orders_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_orders
-- ----------------------------
INSERT INTO `task_orders` VALUES (1, 1, 2, '教学计划制定', '计划制订', '教务处', '完成计算机教学团队的教学计划制定', 'high', '2024-01-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:24:36');
INSERT INTO `task_orders` VALUES (2, 2, 3, '心理调研', '调研', '学生活动中心', '开展学生管理团队的学生心理调研', 'medium', '2024-02-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:24:48');
INSERT INTO `task_orders` VALUES (3, 3, 4, '科研项目进度', '进度推进', '物理科', '推进物理科研团队的科研项目进度', 'high', '2024-03-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:24:56');
INSERT INTO `task_orders` VALUES (4, 4, 5, '年度工作计划', '工作计划', '教务处', '制定系级管理团队的年度工作计划', 'high', '2024-04-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:25:10');
INSERT INTO `task_orders` VALUES (5, 5, 6, '教学资源配置', '资源配置', '人事部', '优化综合教学团队的教学资源配置', 'medium', '2024-05-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:25:30');
INSERT INTO `task_orders` VALUES (6, 6, 7, '培训活动', '培训活动', '人事部', '组织学生辅助团队的培训活动', 'high', '2024-06-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:25:38');
INSERT INTO `task_orders` VALUES (7, 7, 8, '科研成果整理', '成果整理', '历史科', '完善历史教学科研团队的科研成果整理', 'medium', '2024-07-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:26:20');
INSERT INTO `task_orders` VALUES (8, 8, 9, '服务质量', '质量提升', '行政科', '提升行政服务团队的服务质量', 'high', '2024-08-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:26:26');
INSERT INTO `task_orders` VALUES (9, 9, 10, '改进教学方法', '方法改进', '英语科', '改进英语教学团队的教学方法', 'high', '2024-09-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:26:31');
INSERT INTO `task_orders` VALUES (10, 10, 1, '加强管理工作', '管理加强', '教务处', '加强辅导员团队的学生管理工作', 'medium', '2024-10-01', 'pending', '2025-06-14 17:31:15', '2025-06-27 18:26:36');

-- ----------------------------
-- Table structure for task_result_feedbacks
-- ----------------------------
DROP TABLE IF EXISTS `task_result_feedbacks`;
CREATE TABLE `task_result_feedbacks`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_order_id` int NOT NULL,
  `feedback_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `feedback_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_order_id`(`task_order_id` ASC) USING BTREE,
  CONSTRAINT `task_result_feedbacks_ibfk_1` FOREIGN KEY (`task_order_id`) REFERENCES `task_orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_result_feedbacks
-- ----------------------------
INSERT INTO `task_result_feedbacks` VALUES (1, 1, '教学计划初稿内容较为全面，但部分细节需要调整。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (2, 2, '调研方案设计合理，可按计划开展。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (3, 3, '科研项目实验进展顺利，预计能按时完成。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (4, 4, '工作计划框架基本合理，需进一步完善。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (5, 5, '教学资源配置优化有一定成效，但仍有提升空间。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (6, 6, '培训活动筹备有序，期待取得良好效果。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (7, 7, '科研成果整理工作进展正常，需加快进度。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (8, 8, '服务质量提升方案有一定思路，需细化实施步骤。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (9, 9, '教学方法改进调研有一定收获，可深入研究。', '2025-06-14 17:31:15');
INSERT INTO `task_result_feedbacks` VALUES (10, 10, '学生管理工作加强措施讨论有一定成果，需尽快落实。', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for team_analysis_reports
-- ----------------------------
DROP TABLE IF EXISTS `team_analysis_reports`;
CREATE TABLE `team_analysis_reports`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `report_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `team_id`(`team_id` ASC) USING BTREE,
  CONSTRAINT `team_analysis_reports_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team_analysis_reports
-- ----------------------------
INSERT INTO `team_analysis_reports` VALUES (1, 1, '该团队教学能力较强，科研方面有待加强。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (2, 2, '团队成员沟通协作良好，学生管理工作有序开展。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (3, 3, '科研实力雄厚，团队合作默契。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (4, 4, '管理能力突出，决策合理有效。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (5, 5, '教学和管理能力兼备，团队凝聚力强。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (6, 6, '成员学习能力和执行力强，团队活力足。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (7, 7, '教学和科研能力均衡，团队协作效率高。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (8, 8, '服务意识和管理能力强，团队协作良好。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (9, 9, '学术水平高和教学经验丰富，团队合作紧密。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_analysis_reports` VALUES (10, 10, '学生管理能力和沟通能力强，团队配合默契。', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for team_analysis_results
-- ----------------------------
DROP TABLE IF EXISTS `team_analysis_results`;
CREATE TABLE `team_analysis_results`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `requirement_id` bigint NOT NULL,
  `recommended_staff_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '推荐的人员ID列表（JSON格式）',
  `analysis_report` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '班子结构分析报告',
  `match_score` int NULL DEFAULT NULL COMMENT '匹配度评分（0-100）',
  `age_distribution` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄分布',
  `gender_ratio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别比例',
  `skill_coverage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '技能覆盖度',
  `team_structure` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '团队结构（JSON格式，包含职称、学历等分布）',
  `recommendation_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '推荐原因（JSON格式）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_requirement_id`(`requirement_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  CONSTRAINT `fk_analysis_requirement` FOREIGN KEY (`requirement_id`) REFERENCES `team_requirements` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_analysis_results
-- ----------------------------

-- ----------------------------
-- Table structure for team_dynamic_monitoring
-- ----------------------------
DROP TABLE IF EXISTS `team_dynamic_monitoring`;
CREATE TABLE `team_dynamic_monitoring`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `personnel_changes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `profile_changes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `risk_assessment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `optimization_suggestions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `team_id`(`team_id` ASC) USING BTREE,
  CONSTRAINT `team_dynamic_monitoring_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team_dynamic_monitoring
-- ----------------------------
INSERT INTO `team_dynamic_monitoring` VALUES (1, 1, '无', '无', '科研进度可能受设备限制', '申请科研设备更新', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (2, 2, '无', '无', '学生数量增加可能导致管理压力增大', '增加辅导员助理', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (3, 3, '无', '无', '科研竞争激烈，可能影响成果发表', '加强与外界合作', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (4, 4, '无', '无', '管理决策可能受限于信息不全面', '建立信息共享机制', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (5, 5, '无', '无', '教学任务繁重，可能影响教学质量', '合理分配教学任务', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (6, 6, '无', '无', '新成员经验不足，可能影响工作效率', '开展培训活动', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (7, 7, '无', '无', '教学和科研平衡可能难以把握', '制定合理工作计划', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (8, 8, '无', '无', '行政服务需求增加，可能导致服务质量下降', '优化服务流程', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (9, 9, '无', '无', '学术竞争激烈，可能影响学术地位', '鼓励创新研究', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_dynamic_monitoring` VALUES (10, 10, '无', '无', '学生问题增多，可能影响管理效果', '加强学生心理辅导', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for team_members
-- ----------------------------
DROP TABLE IF EXISTS `team_members`;
CREATE TABLE `team_members`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `personnel_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `team_id`(`team_id` ASC) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `team_members_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `team_members_ibfk_2` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team_members
-- ----------------------------
INSERT INTO `team_members` VALUES (1, 1, 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (2, 1, 4, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (3, 2, 2, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (4, 2, 6, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (5, 3, 3, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (6, 3, 7, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (7, 4, 5, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (8, 4, 9, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (9, 5, 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_members` VALUES (10, 5, 8, '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for team_requirements
-- ----------------------------
DROP TABLE IF EXISTS `team_requirements`;
CREATE TABLE `team_requirements`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `team_size` int NULL DEFAULT NULL,
  `position_requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `ability_requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `teamwork_ability_requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `need_team_formation` tinyint(1) NULL DEFAULT 0,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `team_requirements_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team_requirements
-- ----------------------------
INSERT INTO `team_requirements` VALUES (1, 1, 5, '教师、实验员', '具备专业知识和教学能力', '良好的团队协作精神', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (2, 2, 3, '辅导员、行政人员', '沟通能力和组织能力强', '能够有效协作', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (3, 3, 4, '教授、副教授', '科研能力突出', '团队合作默契', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (4, 4, 2, '系主任、教研室主任', '管理能力和决策能力强', '带领团队发展', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (5, 5, 6, '教师、图书管理员', '教学和管理能力兼备', '团队凝聚力强', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (6, 6, 3, '辅导员助理、实验员', '学习能力和执行力强', '积极参与团队工作', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (7, 7, 4, '副教授、讲师', '教学和科研能力均衡', '团队协作效率高', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (8, 8, 2, '行政人员、图书管理员', '服务意识和管理能力强', '团队协作良好', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (9, 9, 5, '教授、教师', '学术水平高和教学经验丰富', '团队合作紧密', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_requirements` VALUES (10, 10, 3, '辅导员、辅导员助理', '学生管理能力和沟通能力强', '团队配合默契', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for team_screening_results
-- ----------------------------
DROP TABLE IF EXISTS `team_screening_results`;
CREATE TABLE `team_screening_results`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `requirement_id` bigint NOT NULL,
  `personnel_id` int NOT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ability_match_score` decimal(5, 2) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `requirement_id`(`requirement_id` ASC) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `fk_screening_requirement` FOREIGN KEY (`requirement_id`) REFERENCES `team_requirements` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `team_screening_results_ibfk_2` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team_screening_results
-- ----------------------------
INSERT INTO `team_screening_results` VALUES (1, 1, 1, '教师', 85.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (2, 1, 4, '实验员', 80.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (3, 2, 2, '辅导员', 90.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (4, 2, 6, '行政人员', 88.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (5, 3, 3, '教授', 95.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (6, 3, 7, '副教授', 92.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (7, 4, 5, '系主任', 90.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (8, 4, 9, '教研室主任', 87.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (9, 5, 1, '教师', 85.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `team_screening_results` VALUES (10, 5, 8, '图书管理员', 82.00, '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for teams
-- ----------------------------
DROP TABLE IF EXISTS `teams`;
CREATE TABLE `teams`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `requirement_id` bigint NOT NULL,
  `team_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `requirement_id`(`requirement_id` ASC) USING BTREE,
  CONSTRAINT `fk_team_requirement` FOREIGN KEY (`requirement_id`) REFERENCES `team_requirements` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teams
-- ----------------------------
INSERT INTO `teams` VALUES (1, 1, '计算机教学团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (2, 2, '学生管理团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (3, 3, '物理科研团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (4, 4, '系级管理团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (5, 5, '综合教学团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (6, 6, '学生辅助团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (7, 7, '历史教学科研团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (8, 8, '行政服务团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (9, 9, '英语教学团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `teams` VALUES (10, 10, '辅导员团队', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for training_personnel_lists
-- ----------------------------
DROP TABLE IF EXISTS `training_personnel_lists`;
CREATE TABLE `training_personnel_lists`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `training_course` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `personnel_id` int NOT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `training_priority` int NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `personnel_id`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `training_personnel_lists_ibfk_1` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of training_personnel_lists
-- ----------------------------
INSERT INTO `training_personnel_lists` VALUES (1, '科研项目管理培训课程', 1, '教师', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (2, '学生心理辅导培训课程', 2, '辅导员', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (3, '量子计算前沿研究培训课程', 3, '教授', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (4, '化学实验新技术培训课程', 4, '实验员', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (5, '团队管理创新培训课程', 5, '系主任', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (6, '行政管理信息化培训课程', 6, '行政人员', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (7, '历史研究新方法培训课程', 7, '副教授', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (8, '图书馆信息化管理培训课程', 8, '图书管理员', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (9, '英语教学新方法培训课程', 9, '教授', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `training_personnel_lists` VALUES (10, '学生管理基础培训课程', 10, '辅导员助理', 1, '2025-06-14 17:31:15', '2025-06-14 17:31:15');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin1', 'password1', 'super_admin', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (2, 'leader2', 'password2', 'campus_leader', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (3, 'specialist3', 'password3', 'personnel_specialist', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (4, 'head4', 'password4', 'department_head', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (5, 'cadre5', 'password5', 'cadre', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (6, 'admin6', 'password6', 'super_admin', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (7, 'leader7', 'password7', 'campus_leader', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (8, 'specialist8', 'password8', 'personnel_specialist', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (9, 'head9', 'password9', 'department_head', '2025-06-14 17:31:15', '2025-06-14 17:31:15');
INSERT INTO `users` VALUES (10, 'cadre10', 'password10', 'cadre', '2025-06-14 17:31:15', '2025-06-14 17:31:15');

SET FOREIGN_KEY_CHECKS = 1;
