SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE food DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE  (
	ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
	HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
	PORT VARCHAR(64) NOT NULL COMMENT 'port',
	TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
	LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
	MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
	CREATED TIMESTAMP NOT NULL COMMENT 'created time',
	PRIMARY KEY(ID)
) COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食资讯文章（主要是美食活动介绍、一些头条大咖、网红大咖的美食介绍）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_type_id` int(11) NULL DEFAULT NULL COMMENT '食物分类大类型id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父节点id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_parent`(`parent_id`) USING BTREE,
  INDEX `index_cate_type`(`category_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 142 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category_item
-- ----------------------------
DROP TABLE IF EXISTS `category_item`;
CREATE TABLE `category_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类属性名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_category`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 710 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食分类属性库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category_type
-- ----------------------------
DROP TABLE IF EXISTS `category_type`;
CREATE TABLE `category_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '美食大分类',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食分类大类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `food_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食名称',
  `food_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食顶图链接地址',
  `food_info` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食介绍',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食介绍（介绍各地美食，按照地区来区分美食）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for food_item
-- ----------------------------
DROP TABLE IF EXISTS `food_item`;
CREATE TABLE `food_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_item_id` int(11) NULL DEFAULT NULL COMMENT '分类属性id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '美食分类属性（美食对应的分类属性库，打的属性标签库）' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**第一大分类：美食种类类型**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'谷类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'薯类、淀粉及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'干豆类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'蔬菜类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'菌藻类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'水果类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'坚果、种子类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'畜肉类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'禽肉类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'乳类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'蛋类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'水产类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'特殊膳食用食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'休闲食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'速食食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'饮料和冷饮类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'含酒精饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'油脂类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'调味品类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,0,'其他',NOW(),NOW());
/**第二大分类：美食营养特点**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (2,0,'谷类及薯类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (2,0,'动物性食物',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (2,0,'豆类及其制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (2,0,'蔬菜水果类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (2,0,'纯热能食物',NOW(),NOW());
/**第三大分类：美食保藏方法**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'罐头食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'脱水干制食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'冷冻食品或冻制食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'冷冻脱水食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'腌渍食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (3,0,'烟熏食品',NOW(),NOW());
/**第四大分类：美食原料种类**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (4,0,'果蔬制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (4,0,'肉禽制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (4,0,'水产制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (4,0,'乳制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (4,0,'粮食制品',NOW(),NOW());
/**第五大分类：美食加工方法**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (5,0,'焙烤制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (5,0,'膨化食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (5,0,'油炸食品',NOW(),NOW());
/**第六大分类：美食食用人群**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (6,0,'婴幼儿食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (6,0,'中小学生食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (6,0,'孕妇、哺乳期妇女以及恢复产后生理功能等特点食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (6,0,'适用于特殊人群需要的特殊营养食品',NOW(),NOW());
/**第一大分类：美食种类类型 二级分类**/
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'小麦',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'稻米',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'玉米',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'大麦',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'小米、黄米',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='谷类及其制品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='薯类、淀粉及其制品'),'薯类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='薯类、淀粉及其制品'),'淀粉类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'大豆',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'绿豆',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'赤豆',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'芸豆',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'蚕豆',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='干豆类及其制品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'根菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'鲜豆类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'茄果、瓜菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'葱蒜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'嫩茎、叶、花菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'水生蔬菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'薯芋类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蔬菜类及其制品'),'野生蔬菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='菌藻类'),'菌类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='菌藻类'),'藻类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'仁果类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'核果类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'浆果类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'柑橘类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'热带、亚热带水果',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水果类及其制品'),'瓜果类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='坚果、种子类'),'树坚果',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='坚果、种子类'),'种子',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'猪',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'牛',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'羊',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'驴',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'马',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='畜肉类及其制品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='禽肉类及其制品'),'鸡',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='禽肉类及其制品'),'鸭',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='禽肉类及其制品'),'鹅',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='禽肉类及其制品'),'火鸡',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='禽肉类及其制品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'液态乳',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'奶粉',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'发酵乳',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'奶酪',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'奶油',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='乳类及其制品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蛋类及其制品'),'鸡蛋',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蛋类及其制品'),'鸭蛋',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蛋类及其制品'),'鹅蛋',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='蛋类及其制品'),'鹌鹑蛋',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水产类'),'鱼',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水产类'),'虾',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水产类'),'蟹',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水产类'),'贝',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='水产类'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'婴幼儿配方粉',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'婴幼儿辅助食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'孕产妇配方粉',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'膳食补充剂',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'特殊医学用途配方食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'低能量配方食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='特殊膳食用食品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'膨化食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'糕点、甜点',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'饼干、面包',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'糖果',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'果脯和蜜饯',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='休闲食品'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='速食食品'),'快餐食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='速食食品'),'方便食品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='速食食品'),'小吃',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'碳酸饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'果汁及果汁饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'蔬菜汁饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'含乳饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'植物蛋白饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'茶叶、咖啡等及其饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'其他固体饮料',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'冰棒、冰激凌类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='饮料和冷饮类'),'其他',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='含酒精饮料'),'发酵酒',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='含酒精饮料'),'蒸馏酒',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='含酒精饮料'),'配制酒',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='油脂类'),'动物油脂',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='油脂类'),'植物油',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'醋',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'酱油',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'酱及酱制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'料酒及制品',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'腐乳',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'咸菜类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'香辛料类',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'糖',NOW(),NOW());
insert into category(category_type_id,parent_id,category_name,create_time,update_time) values (1,(select x.id from category x where x.category_name='调味品类'),'盐、味精及其他',NOW(),NOW());

/**二级分类 分类属性库**/
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'面粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'面条',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'馒头花卷',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'大饼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'烧饼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'其他面饼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小麦'),'其他制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='稻米'),'大米',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='稻米'),'米饭',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='稻米'),'米粥',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='稻米'),'米粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='稻米'),'其他制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='玉米'),'玉米',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='玉米'),'玉米面',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='玉米'),'玉米粒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='玉米'),'其他制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大麦'),'大麦面粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大麦'),'大麦制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小米、黄米'),'小米',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小米、黄米'),'黄米',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小米、黄米'),'小米制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小米、黄米'),'黄米制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯类'),'马铃薯',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯类'),'甘薯',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯类'),'木薯',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='淀粉类'),'食用淀粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='淀粉类'),'粉丝',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='淀粉类'),'粉条',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='淀粉类'),'藕粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='淀粉类'),'其他淀粉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大豆'),'大豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大豆'),'豆腐',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大豆'),'豆干制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大豆'),'豆浆类制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='大豆'),'其他制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='绿豆'),'绿豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='绿豆'),'绿豆制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='赤豆'),'赤豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='赤豆'),'赤豆制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='芸豆'),'芸豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='芸豆'),'芸豆制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蚕豆'),'蚕豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蚕豆'),'蚕豆制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='根菜类'),'萝卜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='根菜类'),'其他根菜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'菜豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'刀豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'蚕豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'豌豆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'其他豆荚类蔬菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'豆芽',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鲜豆类'),'豆苗',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'辣椒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'茄子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'西红柿',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'冬瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'黄瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'苦瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'南瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茄果、瓜菜类'),'其他茄果类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'大蒜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'青蒜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'蒜薹',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'蒜黄',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'其他蒜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'大葱',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'洋葱',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'其他葱类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'姜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='葱蒜类'),'其他',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'菜花',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'西兰花',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'韭菜花',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'其他花菜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'芹菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'菜苔',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'其他茎菜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'白菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'青菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'香菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'韭菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='嫩茎、叶、花菜类'),'其他叶菜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='水生蔬菜类'),'慈姑',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='水生蔬菜类'),'菱角',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='水生蔬菜类'),'藕',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='水生蔬菜类'),'茭白',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯芋类'),'豆薯',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯芋类'),'山药',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='薯芋类'),'芋头',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='野生蔬菜类'),'苜蓿',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='野生蔬菜类'),'地肤',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='野生蔬菜类'),'蕨菜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='野生蔬菜类'),'鱼腥草',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='菌类'),'菌类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='藻类'),'藻类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='仁果类'),'苹果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='仁果类'),'梨',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='仁果类'),'山楂',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='仁果类'),'海棠果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'杏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'梅',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'李',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'樱桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='核果类'),'枣',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'草莓',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'葡萄',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'猕猴桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'沙棘',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'醋栗',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'石榴',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'无花果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'柿子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='浆果类'),'桑葚',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='柑橘类'),'橙',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='柑橘类'),'柑橘',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='柑橘类'),'柚',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='柑橘类'),'柠檬',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'香蕉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'菠萝',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'芒果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'椰子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'番石榴',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'荔枝',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'枇杷',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='热带、亚热带水果'),'杨桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='瓜果类'),'西瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='瓜果类'),'甜瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='瓜果类'),'哈密瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='瓜果类'),'黄金瓜',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'杏仁',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'腰果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'榛子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'山核桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'松子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'核桃',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'板栗',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='树坚果'),'白果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='种子'),'花生',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='种子'),'葵花子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='种子'),'南瓜子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='种子'),'西瓜子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='猪'),'生猪肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='猪'),'猪内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='猪'),'猪肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='牛'),'生牛肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='牛'),'牛内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='牛'),'牛肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='羊'),'生羊肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='羊'),'羊内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='羊'),'羊肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='驴'),'生驴肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='驴'),'驴内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='驴'),'驴肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='马'),'生马肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='马'),'马内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='马'),'马肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸡'),'生鸡肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸡'),'鸡内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸡'),'鸡肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸭'),'生鸭肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸭'),'鸭内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸭'),'鸭肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鹅'),'生鹅肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鹅'),'鹅内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鹅'),'鹅肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='火鸡'),'生火鸡肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='火鸡'),'火鸡内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='火鸡'),'火鸡肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='液态乳'),'巴氏杀菌乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='液态乳'),'灭菌乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='液态乳'),'调制乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶粉'),'全脂奶粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶粉'),'脱脂奶粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶粉'),'加糖奶粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶粉'),'调味奶粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵乳'),'原味酸奶',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵乳'),'调味酸奶',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵乳'),'果粒酸奶',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶酪'),'奶酪',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='奶油'),'奶油',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸡蛋'),'鸡蛋',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鸭蛋'),'鸭蛋',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鹅蛋'),'鹅蛋',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鹌鹑蛋'),'鹌鹑蛋',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='鱼'),'鱼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='虾'),'虾',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蟹'),'蟹',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='贝'),'贝',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿配方粉'),'婴幼儿配方粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'米粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'面条',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'饼干',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'果蔬泥',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'肉松/酥',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='婴幼儿辅助食品'),'其他',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='孕产妇配方粉'),'孕产妇配方粉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='膳食补充剂'),'膳食补充剂',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='特殊医学用途配方食品'),'特殊医学用途配方食品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='低能量配方食品'),'低能量配方食品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='膨化食品'),'锅巴',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='膨化食品'),'虾条',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糕点、甜点'),'月饼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糕点、甜点'),'蛋糕',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糕点、甜点'),'糕点',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='饼干、面包'),'饼干、面包',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖果'),'胶基糖果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖果'),'糖基糖果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖果'),'巧克力基糖果',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果脯和蜜饯'),'果脯和蜜饯',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'盖浇饭',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'炒饭',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'牛肉面',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'汉堡包',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'热狗',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='快餐食品'),'比萨',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'早餐谷物',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'八宝饭',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'其他即食谷物',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'方便面',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'方便米饭',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'其他方便米面制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'速冻包子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'饺子',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'汤圆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'馄饨',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='方便食品'),'其他冷冻米面制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小吃'),'炒干',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小吃'),'凉皮',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小吃'),'煎饼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='小吃'),'麻团',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='碳酸饮料'),'碳酸饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'果汁',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'果浆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'浓缩果汁',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'浓缩果浆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'果肉饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'果汁饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'果粒果汁饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='果汁及果汁饮料'),'水果饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'蔬菜汁',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'蔬菜汁饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'复合果蔬汁',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'发酵果蔬汁饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'食用菌饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蔬菜汁饮料'),'藻类饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='含乳饮料'),'配置型含乳饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='含乳饮料'),'发酵型含乳饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='植物蛋白饮料'),'豆乳类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='植物蛋白饮料'),'椰子乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='植物蛋白饮料'),'杏仁乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='茶叶、咖啡等及其饮料'),'茶叶、咖啡等及其饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='其他固体饮料'),'其他固体饮料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='冰棒、冰激凌类'),'冰棒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='冰棒、冰激凌类'),'冰激凌',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵酒'),'啤酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵酒'),'葡萄酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵酒'),'果酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='发酵酒'),'黄酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蒸馏酒'),'白酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蒸馏酒'),'白兰地',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蒸馏酒'),'威士忌',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蒸馏酒'),'伏特加',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='蒸馏酒'),'朗姆酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='配制酒'),'配制酒',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='动物油脂'),'动物油脂',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='植物油'),'植物油',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='醋'),'醋',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='酱油'),'酱油',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='酱及酱制品'),'酱及酱制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='料酒及制品'),'料酒及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='腐乳'),'腐乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='咸菜类'),'咸菜类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='香辛料类'),'香辛料类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'白糖',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'红糖',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'冰糖',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'葡萄糖',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'果糖',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'糖浆（原料）',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='糖'),'其他甜味料',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values((select x.id from category x where x.category_type_id=1 and x.category_name='盐、味精及其他'),'盐、味精及其他',NOW(),NOW());

insert into category_item(category_id,item_name,create_time,update_time) values(50,'高粱及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(50,'荞麦及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(50,'糜子及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(50,'莜麦及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(50,'薏米及制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(50,'其他',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(58,'其他豆类',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(58,'其他豆类制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(82,'生肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(82,'内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(82,'肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(87,'生肉',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(87,'内脏',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(87,'肉制品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(93,'炼乳',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(93,'奶片',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(93,'奶豆腐',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(93,'奶皮',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(102,'海参',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(102,'海蜇',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(102,'鱿鱼',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(102,'海肠',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(102,'海胆',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(109,'保健食品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(109,'运动型食品',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(115,'海苔',NOW(),NOW());
insert into category_item(category_id,item_name,create_time,update_time) values(115,'果蔬干',NOW(),NOW());

