/*lalla
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : springboot-vue2

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 28/10/2022 12:12:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark`  (
  `id` int(0) NOT NULL,
  `Project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '研究类别',
  `Project_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '研究级别',
  `Project_mark` int(0) NULL DEFAULT NULL COMMENT '项目总积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mark
-- ----------------------------
INSERT INTO `mark` VALUES (1, '计划科研项目', '重大', 300);
INSERT INTO `mark` VALUES (2, '计划科研项目', '重点', 180);
INSERT INTO `mark` VALUES (3, '专项任务', '重大', 200);
INSERT INTO `mark` VALUES (4, '专项任务', '重点', 120);

-- ----------------------------
-- Table structure for office
-- ----------------------------
DROP TABLE IF EXISTS `office`;
CREATE TABLE `office`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `officename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `officeuserid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`, `officename`) USING BTREE,
  INDEX `id_2`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of office
-- ----------------------------
INSERT INTO `office` VALUES (1, '1室', '杨主任', 2087);
INSERT INTO `office` VALUES (2, '2室', '杨主任', 2);
INSERT INTO `office` VALUES (3, '3室', '李艳', 4);
INSERT INTO `office` VALUES (4, '3室', '21', 5);
INSERT INTO `office` VALUES (5, '5', '5', 6);
INSERT INTO `office` VALUES (6, '6', '6', 7);
INSERT INTO `office` VALUES (7, NULL, NULL, 4354354);
INSERT INTO `office` VALUES (8, NULL, NULL, 2082);
INSERT INTO `office` VALUES (9, NULL, NULL, 654654);
INSERT INTO `office` VALUES (10, NULL, NULL, 22);
INSERT INTO `office` VALUES (11, NULL, NULL, 24);
INSERT INTO `office` VALUES (12, NULL, NULL, 2);
INSERT INTO `office` VALUES (13, NULL, NULL, 3);
INSERT INTO `office` VALUES (14, NULL, NULL, 1);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `Project_id` int(0) NOT NULL AUTO_INCREMENT,
  `Project_itemid` int(0) NULL DEFAULT NULL COMMENT '项目编号',
  `Project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '项目名称',
  `Group_id` int(0) NULL DEFAULT NULL COMMENT '组号',
  `Project_detial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究内容',
  `Project_cycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究周期',
  `Project_progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '项目进度 关系到积分发放',
  `Project_product` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '成果形式',
  `Project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究类别',
  `Project_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究级别',
  `Project_mark` float NULL DEFAULT NULL COMMENT '项目总积分',
  `First_mark` float NULL DEFAULT NULL COMMENT '第一阶段积分',
  `First` float NULL DEFAULT NULL COMMENT '第一阶段积分比',
  `Second_mark` float NULL DEFAULT NULL COMMENT '第二阶段积分',
  `Second` float NULL DEFAULT NULL COMMENT '第二阶段积分比',
  `Three_mark` float NULL DEFAULT NULL COMMENT '第三阶段积分',
  `Three` float NULL DEFAULT NULL COMMENT '第三阶段积分比',
  `Project_reward` float NULL DEFAULT NULL COMMENT '附加分',
  `Projectgroup_id` int(0) NULL DEFAULT NULL COMMENT '外键引入userid',
  PRIMARY KEY (`Project_id`) USING BTREE,
  INDEX `Project_itemid`(`Project_itemid`) USING BTREE,
  INDEX `Projectgroup_id`(`Projectgroup_id`) USING BTREE,
  CONSTRAINT `Projectgroup_id` FOREIGN KEY (`Projectgroup_id`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (35, 1, '莫有通讯有限责任公司', 1, '项目出现延期', '20天', '中期', '电脑', '计划科研项目', '重大', 300, 60, 20, 60, 20, 60, 20, 222, 2082);
INSERT INTO `project` VALUES (36, 2, '杰宏有限责任公司', 2, '项目进度正常', '15天', '后期', '电脑', '计划科研项目', '重点', 180, 54, 30, 39.6, 22, 39.6, 22, 800, 2089);
INSERT INTO `project` VALUES (73, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '计划科研项目', '重大', 300, 0, 0, 0, 0, 0, 0, NULL, 2085);

-- ----------------------------
-- Table structure for projectgroup
-- ----------------------------
DROP TABLE IF EXISTS `projectgroup`;
CREATE TABLE `projectgroup`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `projectid` int(0) NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  `mark` int(0) NULL DEFAULT NULL,
  `handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作员',
  `Project_progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '项目进程',
  `office_director` int(0) NULL DEFAULT NULL COMMENT '室主任确认字段 0表示未确认1表示已经确认',
  `scientific` int(0) NULL DEFAULT NULL COMMENT '科研处确认字段 0 表示未确认 1表示已确认',
  `first_Mark` float NULL DEFAULT NULL COMMENT '成员在该项目第一阶段的个人积分',
  `second_Mark` float NULL DEFAULT NULL COMMENT '成员在该项目第二阶段的个人积分',
  `three_Mark` float NULL DEFAULT NULL COMMENT '成员在该项目第三阶段的个人积分',
  `first` float NULL DEFAULT NULL,
  `second` float NULL DEFAULT NULL,
  `three` float NULL DEFAULT NULL,
  `Group_Reward` float NULL DEFAULT NULL COMMENT '项目组奖励积分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `projectid`(`projectid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `projectid` FOREIGN KEY (`projectid`) REFERENCES `project` (`Project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of projectgroup
-- ----------------------------
INSERT INTO `projectgroup` VALUES (21, 35, 2083, 824, '李四', '中期', 0, 0, 12, 12, 12, 20, 20, 20, 0);
INSERT INTO `projectgroup` VALUES (22, 36, 2084, 460, '王五', '中期', 0, 1, 5.4, 3.96, 3.564, 10, 10, 9, 10);
INSERT INTO `projectgroup` VALUES (23, 35, 2082, 824, '张三', '后期', 0, 0, 12, 12, 12, 20, 20, 20, 22);
INSERT INTO `projectgroup` VALUES (27, 35, 22, 0, NULL, '刚开始', 0, 0, 24.6, 18, 18, 41, 30, 30, 0);
INSERT INTO `projectgroup` VALUES (30, 35, 23, 0, NULL, '前期', NULL, 0, 12, 12, 12, 20, 20, 20, 0);
INSERT INTO `projectgroup` VALUES (35, 36, 2089, 0, NULL, NULL, NULL, 0, 5.4, 3.96, 3.96, 10, 10, 10, 22);
INSERT INTO `projectgroup` VALUES (36, 36, 2088, 0, NULL, NULL, NULL, 0, 5.4, 3.96, 3.96, 10, 10, 10, 0);
INSERT INTO `projectgroup` VALUES (37, 36, 22, 0, NULL, '一般', NULL, 0, 37.8, 27.72, 28.116, 70, 70, 71, 0);
INSERT INTO `projectgroup` VALUES (45, 35, 2088, 0, NULL, '刚开始', NULL, NULL, 6, 6, 6, 10, 10, 10, 0);
INSERT INTO `projectgroup` VALUES (47, 35, 2089, 0, NULL, '中期', NULL, NULL, 7.2, 12.6, 27, 12, 21, 45, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(0) NOT NULL AUTO_INCREMENT,
  `userjobid` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `role` int(0) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限',
  `mark` int(0) NULL DEFAULT NULL COMMENT '积分',
  `officeid` int(0) NOT NULL COMMENT '所属科室id',
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `userjobid`(`userjobid`) USING BTREE,
  INDEX `officeid`(`officeid`) USING BTREE,
  CONSTRAINT `officeid` FOREIGN KEY (`officeid`) REFERENCES `office` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '888', '方嘉伦', '888', '20-780-9732', 39, 3, '男', '天河区天河路108号', '管理员', '公关部', 629, 1);
INSERT INTO `user` VALUES (4, '32738409', '宋宇宁', 'FOqgocrdqn', '10-240-0912', 95, 3, '男', '东城区东单王府井东街857号', '人力资源经理', '外销部', 220, 2);
INSERT INTO `user` VALUES (5, '9076716', '龙子异', 'hxnvtPCGLI', '10-3251-1048', 37, 3, '男', '西城区复兴门内大街23号', '专案经理', '市场部', 167, 2);
INSERT INTO `user` VALUES (6, '54960551', '冯秀英', '2lwF2Zk1TL', '170-0412-4903', 33, 3, '女', '浦东新区橄榄路22号', '首席运营官', '公关部', 399, 3);
INSERT INTO `user` VALUES (7, '21452627', '汤致远', 'TcricaFvuX', '148-5924-9952', 74, 3, '男', '浦东新区橄榄路23号', '园艺家', '人力资源部', 774, 2);
INSERT INTO `user` VALUES (8, '839339', '顾詩涵', 'ksM8MxWABh', '169-5908-0015', 93, 1, '女', '成华区玉双路6号111号', '作家', '物流部', 64, 2);
INSERT INTO `user` VALUES (10, '64207309', '吴子异', 'BDI1GM5QvR', '28-4943-2789', 82, 1, '男', '成华区双庆路367号', '图象设计师', '信息技术支持部', 87, 3);
INSERT INTO `user` VALUES (11, '45898483', '梁岚', 'CYpE5K2FYK', '172-7080-7367', 52, 1, '男', '白云区小坪东路705号', '私人教练', '研究及开发部', 775, 2);
INSERT INTO `user` VALUES (12, '648628', '金云熙', 'KNgtq0L1Ih', '160-9971-6481', 42, 1, '男', '徐汇区虹桥路528号', '作家', '研究及开发部', 226, 2);
INSERT INTO `user` VALUES (13, '52205638', '韦睿', 'h7fXZJlwQ1', '755-1447-8229', 56, 1, '男', '罗湖区清水河一路790号', '零售助理', '产品质量部', 450, 2);
INSERT INTO `user` VALUES (14, '4688971', '薛晓明', 'HJWJOXFZry', '760-4070-7557', 16, 1, '男', '天河区大信商圈大信南路898号', '数据库经理', '产品质量部', 959, 1);
INSERT INTO `user` VALUES (15, '44404883', '王震南', 'eZm6iv7uft', '10-562-7013', 87, 1, '男', '延庆区028县道1号', '精算师', '采购部', 649, 1);
INSERT INTO `user` VALUES (17, '20489539', '任詩涵', 'QKts3dH1ib', '28-796-9567', 2, 1, '女', '锦江区红星路三段999号', '牙医', '研究及开发部', 690, 1);
INSERT INTO `user` VALUES (18, '25643805', '黎子异', 'qCkwGuJz74', '149-8077-8477', 73, 1, '男', '海淀区清河中街68号926号', '生物化学家', '生产部', 437, 2);
INSERT INTO `user` VALUES (19, '64594866', '邹云熙', '26rI5n8rnY', '10-3956-0056', 78, 1, '男', '房山区岳琉路966号', '图书馆管理员', '会计及金融部', 583, 2);
INSERT INTO `user` VALUES (20, '9504962', '武岚', 'HASkZ2OnQs', '760-0432-9823', 90, 1, '女', '乐丰六路441号', '客户服务经理', '工程部', 484, 2);
INSERT INTO `user` VALUES (22, '64340130', '吴秀英', 'UZcI8jMZxN', '163-1350-8297', 1, 1, '女', '西城区西長安街515号', '首席运营官', '行政管理部', 231, 1);
INSERT INTO `user` VALUES (23, '40204480', '姚岚', 'EU9SnjGDMl', '10-877-4081', 95, 1, '女', '房山区岳琉路754号', '专案经理', '研究及开发部', 8, 1);
INSERT INTO `user` VALUES (2082, '123456', 'triste', '123456', '18392945246', 22, 1, '男', '陕西省西安市', '经理', '技术部', 520, 2);
INSERT INTO `user` VALUES (2083, '123', '迟意', '123456', '18291345653', 22, 1, '男', '陕西省渭南市', '普通员工', '信息部', 820, 1);
INSERT INTO `user` VALUES (2084, '12345', '听闻', '123456', '18294562463', 22, 1, '女', '陕西省西安市', '总监', '技术部', 824, 1);
INSERT INTO `user` VALUES (2085, '12', '大飒', '123456', '18455465435', 25, 2, '女', '陕西省安康市', '总监', '技术部', 864, 2);
INSERT INTO `user` VALUES (2086, '45', '张三', '123456', '456454645', 21, 1, '女', '北京市', '经理', '科技部', 260, 1);
INSERT INTO `user` VALUES (2087, '1', '李四', '1', '18542346452', 21, 3, '男', '陕西省咸阳市', '经理', '技术部', 820, 1);
INSERT INTO `user` VALUES (2088, '11', '普通员工', '11', '16313508291', 11, 1, '男', '陕西省西安市', '教授', '工程部', 111, 2);
INSERT INTO `user` VALUES (2089, '22', '项目组长', '22', '16313508292', 22, 1, '女', '陕西省渭南市', '经理', '科技部', 222, 1);
INSERT INTO `user` VALUES (2090, '33', '室主任', '33', '16313508293', 33, 1, '男', '陕西省咸阳市', '总监', '技术部', 333, 2);
INSERT INTO `user` VALUES (2091, '44', '科研处', '44', '16313508294', 44, 1, '女', '陕西省安康市', '董事长', '信息部', 444, 1);
INSERT INTO `user` VALUES (2092, '55', '后台管理员', '55', '16313508295', 55, 1, '男', '陕西省西安市', '程序员', '开发部', 555, 1);

SET FOREIGN_KEY_CHECKS = 1;
