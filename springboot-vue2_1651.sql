/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : springboot-vue2

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 12/10/2022 16:51:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `Project_id` int(0) NOT NULL AUTO_INCREMENT,
  `Project_itemid` int(0) NULL DEFAULT NULL,
  `Project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Group_id` int(0) NULL DEFAULT NULL,
  `Project_detial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究内容',
  `Project_cycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究周期',
  `Project_progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '项目进度 关系到积分发放',
  `Project_product` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '成果形式',
  `Project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究类别',
  `Project_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '研究级别',
  `Project_mark` int(0) NULL DEFAULT NULL COMMENT '项目积分',
  `Project_reward` int(0) NULL DEFAULT NULL COMMENT '奖励积分',
  `Projectgroup_id` int(0) NULL DEFAULT NULL COMMENT '外键引入userid',
  PRIMARY KEY (`Project_id`) USING BTREE,
  INDEX `Project_itemid`(`Project_itemid`) USING BTREE,
  INDEX `Projectgroup_id`(`Projectgroup_id`) USING BTREE,
  CONSTRAINT `Projectgroup_id` FOREIGN KEY (`Projectgroup_id`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (35, 1, '莫有通讯有限责任公司', 1, '项目出现延期', '20天', '中期', '电脑', '临时研究', '一般', 824, 964, 2082);
INSERT INTO `project` VALUES (36, 2, '杰宏有限责任公司', 2, '项目进度正常', '15天', '中期', '电脑', '临时研究', '一般', 752, 800, 2084);
INSERT INTO `project` VALUES (37, 3, '子异有限责任公司', 3, '项目进度正常', '22天', '中期', '电脑', '临时研究', '一般', 560, 680, 2);
INSERT INTO `project` VALUES (38, 4, '宇宁有限责任公司', 4, '项目急需某种木材', '20天', '结束', '电脑', '服务支撑业务', '重点', 256, 669, 5);
INSERT INTO `project` VALUES (39, 5, '致远食品有限责任公司', 5, '项目出现延期', '10天', '结束', '阅览室', '专项任务', '一般', 676, 653, 10);

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `projectid`(`projectid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `projectid` FOREIGN KEY (`projectid`) REFERENCES `project` (`Project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of projectgroup
-- ----------------------------
INSERT INTO `projectgroup` VALUES (21, 35, 2083, 824, '李四', '后期', 1, 1);
INSERT INTO `projectgroup` VALUES (22, 36, 2084, 460, '王五', '中期', 1, 1);
INSERT INTO `projectgroup` VALUES (23, 35, 2082, 820, '张三', '后期', 1, 1);

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
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `userjobid`(`userjobid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2088 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '777', '顾云熙', '777', '13777777777', 27, 2, '女', '9月23号更新浦东新区更新路777号', '管理员', '销售部', 486);
INSERT INTO `user` VALUES (2, '888', '方嘉伦', '888', '20-780-9732', 39, 2, '男', '天河区天河路108号', '管理员', '公关部', 629);
INSERT INTO `user` VALUES (3, '20220927', '许嘉伦', '20220927', '199-0399-5224', 76, 1, '男', '乐丰六路548号', '管理员', '外销部', 258);
INSERT INTO `user` VALUES (4, '32738409', '宋宇宁', 'FOqgocrdqn', '10-240-0912', 95, 2, '男', '东城区东单王府井东街857号', '人力资源经理', '外销部', 220);
INSERT INTO `user` VALUES (5, '9076716', '龙子异', 'hxnvtPCGLI', '10-3251-1048', 37, 2, '男', '西城区复兴门内大街23号', '专案经理', '市场部', 167);
INSERT INTO `user` VALUES (6, '54960551', '冯秀英', '2lwF2Zk1TL', '170-0412-4903', 33, 1, '女', '浦东新区橄榄路22号', '首席运营官', '公关部', 399);
INSERT INTO `user` VALUES (7, '21452627', '汤致远', 'TcricaFvuX', '148-5924-9952', 74, 3, '男', '浦东新区橄榄路23号', '园艺家', '人力资源部', 774);
INSERT INTO `user` VALUES (8, '839339', '顾詩涵', 'ksM8MxWABh', '169-5908-0015', 93, 1, '女', '成华区玉双路6号111号', '作家', '物流部', 64);
INSERT INTO `user` VALUES (9, '34732874', '何震南', '1y6359mbWn', '10-8269-3622', 34, 3, '男', '延庆区028县道795号', '社交媒体协调员', '工程部', 775);
INSERT INTO `user` VALUES (10, '64207309', '吴子异', 'BDI1GM5QvR', '28-4943-2789', 82, 2, '男', '成华区双庆路367号', '图象设计师', '信息技术支持部', 87);
INSERT INTO `user` VALUES (11, '45898483', '梁岚', 'CYpE5K2FYK', '172-7080-7367', 52, 3, '女', '白云区小坪东路705号', '私人教练', '研究及开发部', 775);
INSERT INTO `user` VALUES (12, '648628', '金云熙', 'KNgtq0L1Ih', '160-9971-6481', 42, 2, '男', '徐汇区虹桥路528号', '作家', '研究及开发部', 226);
INSERT INTO `user` VALUES (13, '52205638', '韦睿', 'h7fXZJlwQ1', '755-1447-8229', 56, 3, '男', '罗湖区清水河一路790号', '零售助理', '产品质量部', 450);
INSERT INTO `user` VALUES (14, '4688971', '薛晓明', 'HJWJOXFZry', '760-4070-7557', 16, 2, '男', '天河区大信商圈大信南路898号', '数据库经理', '产品质量部', 959);
INSERT INTO `user` VALUES (15, '44404883', '王震南', 'eZm6iv7uft', '10-562-7013', 87, 2, '男', '延庆区028县道1号', '精算师', '采购部', 649);
INSERT INTO `user` VALUES (16, '48837281', '叶致远', 'GUjnH4naSE', '147-0501-6357', 22, 2, '男', '黄浦区淮海中路126号', '医生', '法律部', 305);
INSERT INTO `user` VALUES (17, '20489539', '任詩涵', 'QKts3dH1ib', '28-796-9567', 2, 1, '女', '锦江区红星路三段999号', '牙医', '研究及开发部', 690);
INSERT INTO `user` VALUES (18, '25643805', '黎子异', 'qCkwGuJz74', '149-8077-8477', 73, 3, '男', '海淀区清河中街68号926号', '生物化学家', '生产部', 437);
INSERT INTO `user` VALUES (19, '64594866', '邹云熙', '26rI5n8rnY', '10-3956-0056', 78, 2, '男', '房山区岳琉路966号', '图书馆管理员', '会计及金融部', 583);
INSERT INTO `user` VALUES (20, '9504962', '武岚', 'HASkZ2OnQs', '760-0432-9823', 90, 3, '女', '乐丰六路441号', '客户服务经理', '工程部', 484);
INSERT INTO `user` VALUES (21, '5530157', '侯子异', 'Xo0kojwoaI', '28-915-2482', 2, 2, '男', '成华区双庆路791号', '水疗经理', '公关部', 882);
INSERT INTO `user` VALUES (22, '64340130', '吴秀英', 'UZcI8jMZxN', '163-1350-8297', 1, 2, '女', '西城区西長安街515号', '首席运营官', '行政管理部', 231);
INSERT INTO `user` VALUES (23, '40204480', '姚岚', 'EU9SnjGDMl', '10-877-4081', 95, 2, '女', '房山区岳琉路754号', '专案经理', '研究及开发部', 8);
INSERT INTO `user` VALUES (2082, '123456', 'triste', '123456', '18392945246', 22, 2, '男', '陕西省西安市', '经理', '技术部', 520);
INSERT INTO `user` VALUES (2083, '123', '迟意', '123456', '18291345653', 22, 1, '男', '陕西省渭南市', '普通员工', '信息部', 820);
INSERT INTO `user` VALUES (2084, '12345', '听闻', '123456', '18294562463', 22, 1, '女', '陕西省西安市', '总监', '技术部', 824);
INSERT INTO `user` VALUES (2085, '12', '大飒', '123456', '18455465435', 25, 5, '女', '陕西省安康市', '总监', '技术部', 864);
INSERT INTO `user` VALUES (2086, '45', '张三', '123456', '456454645', 21, 4, '女', '北京市', '经理', '科技部', 260);
INSERT INTO `user` VALUES (2087, '1', '李四', '1', '18542346452', 21, 3, '男', '陕西省咸阳市', '经理', '技术部', 820);

SET FOREIGN_KEY_CHECKS = 1;
