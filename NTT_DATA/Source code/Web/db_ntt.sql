/*
Navicat MySQL Data Transfer

Source Server         : Royce
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : db_ntt

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-08-14 10:34:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'ICy5YqxZB1uWSwcVLSNLcA==');
INSERT INTO `t_admin` VALUES ('2', 'admin2', 'aY1RoZ2KEhzlgUmde3AWaA==');

-- ----------------------------
-- Table structure for `t_applibonus`
-- ----------------------------
DROP TABLE IF EXISTS `t_applibonus`;
CREATE TABLE `t_applibonus` (
  `projectId` int(11) NOT NULL,
  `financialId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `projectName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `employId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `employName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `depId` int(11) DEFAULT NULL,
  `depName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zhengshiMonth` decimal(11,2) DEFAULT NULL,
  `BPMonth` decimal(11,2) DEFAULT NULL,
  `lianxieMonth` decimal(11,2) DEFAULT NULL,
  `lizhiMonth` decimal(11,2) DEFAULT NULL,
  `applyDate` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `applyDate2` date DEFAULT NULL,
  `bonustotal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bonushuafen` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `centerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_applibonus
-- ----------------------------
INSERT INTO `t_applibonus` VALUES ('1', '5W211011605', '项目1', '123', '张三', null, '2H1B', '40.00', '10.00', '40.00', null, '1-3月', '2016-08-10', '75000', '已划分', '1');
INSERT INTO `t_applibonus` VALUES ('2', '5W211011505', '项目2', '456', '李四', null, '2B2B', '30.00', '10.00', '10.00', null, '4-6月', '2016-08-11', '71400', '未划分', null);

-- ----------------------------
-- Table structure for `t_baoxiao`
-- ----------------------------
DROP TABLE IF EXISTS `t_baoxiao`;
CREATE TABLE `t_baoxiao` (
  `data` date NOT NULL,
  `id` varchar(20) NOT NULL,
  `name` varchar(11) NOT NULL,
  `funds` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_baoxiao
-- ----------------------------
INSERT INTO `t_baoxiao` VALUES ('0000-00-00', '北京', '0.1', '0');
INSERT INTO `t_baoxiao` VALUES ('0000-00-00', '天津', '0.15', '0');
INSERT INTO `t_baoxiao` VALUES ('0000-00-00', '无锡', '0.1', '0.4');
INSERT INTO `t_baoxiao` VALUES ('0000-00-00', '西安', '0.1', '0.1');
INSERT INTO `t_baoxiao` VALUES ('0000-00-00', '长春', '0.1', '0');

-- ----------------------------
-- Table structure for `t_blank`
-- ----------------------------
DROP TABLE IF EXISTS `t_blank`;
CREATE TABLE `t_blank` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blank
-- ----------------------------
INSERT INTO `t_blank` VALUES ('1', '北京', '0.1', '0', '0');
INSERT INTO `t_blank` VALUES ('2', '无锡', '0.1', '0.04', '0.06');
INSERT INTO `t_blank` VALUES ('3', '天津', '0.15', '0', '0.1');
INSERT INTO `t_blank` VALUES ('4', '长春', '0.1', '0', '0');
INSERT INTO `t_blank` VALUES ('5', '西安', '0.1', '0.1', '0');

-- ----------------------------
-- Table structure for `t_bonus`
-- ----------------------------
DROP TABLE IF EXISTS `t_bonus`;
CREATE TABLE `t_bonus` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zhengshiNorm` varchar(11) NOT NULL,
  `bpNorm` varchar(11) NOT NULL,
  `lianxieNorm` varchar(11) NOT NULL,
  `tuizhiNorm` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bonus
-- ----------------------------
INSERT INTO `t_bonus` VALUES ('1', '北京', '1900', '1900', '300', '1900');
INSERT INTO `t_bonus` VALUES ('2', '无锡', '1800', '1800', '300', '1800');
INSERT INTO `t_bonus` VALUES ('3', '天津', '1700', '1700', '300', '1700');
INSERT INTO `t_bonus` VALUES ('4', '长春', '1500', '1500', '300', '1500');
INSERT INTO `t_bonus` VALUES ('5', '西安', '1500', '1500', '300', '1500');

-- ----------------------------
-- Table structure for `t_bp`
-- ----------------------------
DROP TABLE IF EXISTS `t_bp`;
CREATE TABLE `t_bp` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  `xmFund` varchar(11) NOT NULL,
  `sumFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bp
-- ----------------------------
INSERT INTO `t_bp` VALUES ('1', '北京', '10%', '0', '0', '90%', '100%');
INSERT INTO `t_bp` VALUES ('2', '无锡', '10%', '4%', '6%', '8%', '100%');
INSERT INTO `t_bp` VALUES ('3', '天津', '15%', '0', '10%', '75%', '100%');
INSERT INTO `t_bp` VALUES ('4', '长春', '10%', '10%', '0', '80%', '100%');
INSERT INTO `t_bp` VALUES ('5', '西安', '10%', '10%', '0', '80%', '100%');

-- ----------------------------
-- Table structure for `t_charger`
-- ----------------------------
DROP TABLE IF EXISTS `t_charger`;
CREATE TABLE `t_charger` (
  `chargerId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `centerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`chargerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_charger
-- ----------------------------
INSERT INTO `t_charger` VALUES ('1', 'manager1', 'ICy5YqxZB1uWSwcVLSNLcA==', '1');
INSERT INTO `t_charger` VALUES ('2', 'manager2', 'ICy5YqxZB1uWSwcVLSNLcA==', '2');
INSERT INTO `t_charger` VALUES ('3', 'manager3', 'ICy5YqxZB1uWSwcVLSNLcA==', '3');
INSERT INTO `t_charger` VALUES ('4', 'manager4', 'ICy5YqxZB1uWSwcVLSNLcA==', '4');
INSERT INTO `t_charger` VALUES ('5', 'manager5', 'ICy5YqxZB1uWSwcVLSNLcA==', '5');

-- ----------------------------
-- Table structure for `t_fafang`
-- ----------------------------
DROP TABLE IF EXISTS `t_fafang`;
CREATE TABLE `t_fafang` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  `xmFund` varchar(11) NOT NULL,
  `sumFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fafang
-- ----------------------------
INSERT INTO `t_fafang` VALUES ('1', '北京', '0.1', '0', '0', '0.9', '1');
INSERT INTO `t_fafang` VALUES ('2', '无锡', '0.1', '0.04', '0.06', '0.08', '1');
INSERT INTO `t_fafang` VALUES ('3', '天津', '0.15', '0', '0.1', '0.75', '1');
INSERT INTO `t_fafang` VALUES ('4', '长春', '0.1', '0', '0', '0.8', '1');
INSERT INTO `t_fafang` VALUES ('5', '西安', '0.1', '0.1', '0', '0.8', '1');

-- ----------------------------
-- Table structure for `t_lianxie`
-- ----------------------------
DROP TABLE IF EXISTS `t_lianxie`;
CREATE TABLE `t_lianxie` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  `xmFund` varchar(11) NOT NULL,
  `sumFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lianxie
-- ----------------------------
INSERT INTO `t_lianxie` VALUES ('1', '北京', '10%', '0', '0', '90%', '100%');
INSERT INTO `t_lianxie` VALUES ('2', '无锡', '10%', '4%', '6%', '8%', '100%');
INSERT INTO `t_lianxie` VALUES ('3', '天津', '15%', '0', '10%', '75%', '100%');
INSERT INTO `t_lianxie` VALUES ('4', '长春', '10%', '0', '0', '80%', '100%');
INSERT INTO `t_lianxie` VALUES ('5', '西安', '10%', '10%', '0', '80%', '100%');
INSERT INTO `t_lianxie` VALUES ('6', '湖州', '10%', '10%', '10%', '10%', '100%');

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `projectId` int(10) NOT NULL AUTO_INCREMENT,
  `financialId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `projectName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `orderPerformance` decimal(10,0) DEFAULT NULL,
  `requestPerformance` decimal(10,0) DEFAULT NULL,
  `supportPerformance` decimal(10,0) DEFAULT NULL,
  `projectStartDate` date DEFAULT NULL,
  `projectEndDate` date DEFAULT NULL,
  `centerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '5W211011605', '项目1', '50', '50', '40', '2016-08-01', '2016-08-02', '1');

-- ----------------------------
-- Table structure for `t_staff`
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
  `staffId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`staffId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES ('1', 'staff', 'ICy5YqxZB1uWSwcVLSNLcA==');
INSERT INTO `t_staff` VALUES ('2', 'staff2', 'aY1RoZ2KEhzlgUmde3AWaA==');

-- ----------------------------
-- Table structure for `t_tuizhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_tuizhi`;
CREATE TABLE `t_tuizhi` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  `xmFund` varchar(11) NOT NULL,
  `sumFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tuizhi
-- ----------------------------
INSERT INTO `t_tuizhi` VALUES ('1', '北京', '10%', '0', '0', '90%', '100%');
INSERT INTO `t_tuizhi` VALUES ('2', '无锡', '10%', '4%', '6%', '80%', '100%');
INSERT INTO `t_tuizhi` VALUES ('3', '天津', '15%', '0', '10%', '75%', '100%');
INSERT INTO `t_tuizhi` VALUES ('4', '长春', '10%', '10%', '0', '80%', '100%');
INSERT INTO `t_tuizhi` VALUES ('5', '西安', '10%', '10%', '0', '80%', '100%');

-- ----------------------------
-- Table structure for `t_xizemodify`
-- ----------------------------
DROP TABLE IF EXISTS `t_xizemodify`;
CREATE TABLE `t_xizemodify` (
  `projectId` int(11) NOT NULL,
  `centerId` int(11) DEFAULT NULL,
  `centerName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bonustotal` int(11) DEFAULT NULL,
  `zhongxin` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `benbu` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bumen` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pm` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_xizemodify
-- ----------------------------
INSERT INTO `t_xizemodify` VALUES ('1', '2', '无锡', '调整结果', '75000', '6595', '4011', '16977', '47417');
INSERT INTO `t_xizemodify` VALUES ('1', '2', '无锡', '调整额', '75000', '', '', '', '');
INSERT INTO `t_xizemodify` VALUES ('1', '2', '无锡', '按照规则划分', '79011', '10000', '3011', '16000', '50000');

-- ----------------------------
-- Table structure for `t_yue`
-- ----------------------------
DROP TABLE IF EXISTS `t_yue`;
CREATE TABLE `t_yue` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yue
-- ----------------------------
INSERT INTO `t_yue` VALUES ('1', '北京', '0.1', '0', '0');
INSERT INTO `t_yue` VALUES ('2', '无锡', '0.1', '0.04', '0.06');
INSERT INTO `t_yue` VALUES ('3', '天津', '0.15', '0', '0.1');
INSERT INTO `t_yue` VALUES ('4', '长春', '0.1', '0', '0');
INSERT INTO `t_yue` VALUES ('5', '西安', '0.1', '0.1', '0');

-- ----------------------------
-- Table structure for `t_zhengshi`
-- ----------------------------
DROP TABLE IF EXISTS `t_zhengshi`;
CREATE TABLE `t_zhengshi` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  `xmFund` varchar(11) NOT NULL,
  `sumFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zhengshi
-- ----------------------------
INSERT INTO `t_zhengshi` VALUES ('1', '北京', '10%', '0', '0', '90%', '100%');
INSERT INTO `t_zhengshi` VALUES ('2', '无锡', '10%', '4%', '6%', '8%', '100%');
INSERT INTO `t_zhengshi` VALUES ('3', '天津', '15%', '0', '10%', '75%', '100%');
INSERT INTO `t_zhengshi` VALUES ('4', '长春', '10%', '0', '0', '80%', '100%');
INSERT INTO `t_zhengshi` VALUES ('5', '西安', '10%', '10%', '0', '80%', '100%');
INSERT INTO `t_zhengshi` VALUES ('6', '湖州', '10%', '10%', '10%', '10%', '40%');

-- ----------------------------
-- Table structure for `t_zhiqu`
-- ----------------------------
DROP TABLE IF EXISTS `t_zhiqu`;
CREATE TABLE `t_zhiqu` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `zxFund` varchar(11) NOT NULL,
  `bbFund` varchar(11) NOT NULL,
  `bmFund` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zhiqu
-- ----------------------------
INSERT INTO `t_zhiqu` VALUES ('1', '北京', '0.1', '0', '0');
INSERT INTO `t_zhiqu` VALUES ('2', '无锡', '0.1', '0.04', '0.06');
INSERT INTO `t_zhiqu` VALUES ('3', '天津', '0.15', '0', '0.1');
INSERT INTO `t_zhiqu` VALUES ('4', '长春', '0.1', '0', '0');
INSERT INTO `t_zhiqu` VALUES ('5', '西安', '0.1', '0.1', '0');

-- ----------------------------
-- Table structure for `t_zongze`
-- ----------------------------
DROP TABLE IF EXISTS `t_zongze`;
CREATE TABLE `t_zongze` (
  `centerId` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(20) NOT NULL,
  `bpRate` varchar(11) NOT NULL,
  `projectRateUp` varchar(11) NOT NULL,
  `projectRateDown` varchar(11) NOT NULL,
  `shareBonusRateUp` varchar(11) NOT NULL,
  `shareBonusRateDown` varchar(11) NOT NULL,
  `tuijianRate` varchar(11) NOT NULL,
  PRIMARY KEY (`centerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zongze
-- ----------------------------
INSERT INTO `t_zongze` VALUES ('1', '北京', '98%', '12%', '2%', '30%', '39%', '8%');
INSERT INTO `t_zongze` VALUES ('2', '无锡', '15%', '8%', '2%', '98%', '90%', '8%');
INSERT INTO `t_zongze` VALUES ('3', '天津', '25%', '10%', '2%', '98%', '90%', '8%');

-- ----------------------------
-- Table structure for `t_zongzemodify`
-- ----------------------------
DROP TABLE IF EXISTS `t_zongzemodify`;
CREATE TABLE `t_zongzemodify` (
  `projectId` int(11) NOT NULL,
  `centerId` int(11) DEFAULT NULL,
  `centerName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bonustotal` int(11) DEFAULT NULL,
  `proMoneyRatioMax` double DEFAULT NULL,
  `proMoneyMax` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `proMoneyRatioMin` double DEFAULT NULL,
  `proMoneyMin` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `proBonusRatioMin` double DEFAULT NULL,
  `proBonusMin` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `zhongxinMoney` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_zongzemodify
-- ----------------------------
INSERT INTO `t_zongzemodify` VALUES ('1', '2', '无锡', '按照规则划分', '75000', '0.1', '6240', '0.08', '4992', '0.9', '56160', '12600');
