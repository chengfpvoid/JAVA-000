DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id ',
  `cat_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_added_time` datetime(0) DEFAULT NULL COMMENT '上架时间',
  `goods_unadded_time` datetime(0) DEFAULT NULL COMMENT '下架时间',
  `goods_price` decimal(11, 2) DEFAULT NULL COMMENT '销售价（默认sku第一个价格）',
  `goods_desc` text  COMMENT '商品详细介绍',
  `goods_status` tinyint(4) DEFAULT 0 COMMENT '商品状态(-1暂存、0待审核、10审核失败、11待上架、20出售中、21已下架、30处理中)',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL  COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) NOT DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `cat`(`cat_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品spu表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_goods_item`;
CREATE TABLE `t_goods_item`  (
  `goods_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'skuid',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `goods_item_no` varchar(50) NOT NULL COMMENT '货号 skuno',
  `spec_detail_id` varchar(250) NOT NULL COMMENT '货品规格值ID',
  `spec_name` varchar(250)  NOT NULL COMMENT '货品规格值名称',
  `goods_item_added_time` datetime(0) NOT NULL COMMENT '上架时间',
  `goods_item_unadded_time` datetime(0) DEFAULT NULL COMMENT '下架时间',
  `goods_item_price` decimal(11, 2) DEFAULT NULL COMMENT '销售价格',
  `goods_item_cost_price` decimal(11, 2) DEFAULT NULL COMMENT '成本价',
  `goods_item_status` tinyint(4) DEFAULT 0 COMMENT '商品状态(-1暂存、0待审核、10审核失败、11待上架、20出售中、21已下架、30处理中)',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`goods_item_id`) USING BTREE,
  INDEX `goods_item_no`(`goods_item_no`) USING BTREE,
  INDEX `idx_goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku表' ROW_FORMAT = Dynamic;

CREATE TABLE `t_goods_item_stock`  (
  `goods_item_id` bigint(20) NOT NULL COMMENT '商品skuid',
  `stock` bigint(20) NOT NULL COMMENT '商品skuid',
  `version` bigint(20) NOT DEFAULT '0' COMMENT '版本号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`goods_item_id`) USING BTREE,
  INDEX `idx_sku_stock`(`goods_item_id`,`stock`) USING BTREE,
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8_general_ci COMMENT = 'sku库存表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_goods_category`;
CREATE TABLE `t_goods_category`  (
  `cat_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `cat_name` varchar(50) NOT NULL COMMENT '分类名称',
  `cat_parent_id` bigint(20) DEFAULT NULL COMMENT '父级分类id',
  `cat_classes` tinyint(4) NOT NULL COMMENT '分类等级1,2,3',
  `show` tinyint(1) DEFAULT NULL COMMENT '是否显示(0,1)',
  `cat_img_url` varchar(255) DEFAULT NULL COMMENT '分类图片链接',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`cat_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `t_goods_image`;
CREATE TABLE `t_goods_image`  (
  `goods_img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `image_url` varchar(200) NOT NULL COMMENT '图片url',
  `goods_img_sort` int(11) NOT DEFAULT '1' COMMENT '排序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) DEFAULT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`goods_img_id`) USING BTREE,
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品图片表' ROW_FORMAT = Dynamic;


CREATE TABLE `t_goods_spec`  (
  `spec_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `cat_id` bigint(20) NOT NULL COMMENT '分类id',
  `spec_name` varchar(30)  NOT NULL COMMENT '规格名称',
  `spec_sort` int(11) NOT DEFAULT '0' COMMENT '排序',
  `show` tinyint(1) DEFAULT NULL COMMENT '是否显示(0,1)',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) NOT DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`spec_id`) USING BTREE,
  INDEX `idx_spec_cat`(`cat_id``) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类关联规格表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_goods_spec_detail`;
CREATE TABLE `t_goods_spec_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格详情id',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID',
  `spec_detail_name` varchar(255)  NOT NULL COMMENT '规格值名称',
  `spec_sort` int(11) NOT DEFAULT '0' COMMENT '排序',
  `show` tinyint(1) DEFAULT NULL COMMENT '是否显示(0,1)',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_spec`(`spec_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品规格-规格详情表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_goods_rele_spec_detail`;
CREATE TABLE `t_goods_rele_spec_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格id',
  `spec_detail_id` varchar(255)  DEFAULT NULL COMMENT '规格值id列表 用,拼接',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_id` bigint(10) NOT NULL COMMENT '创建人',
  `mod_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mod_id` bigint(10) DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '删除标记 1:已删除状态 0:正常状态',
  `del_id` bigint(10) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'spu与规格值关联表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- 订单相关表结构
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_code` varchar(150) DEFAULT NULL COMMENT '订单编号',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父订单id',
  `is_single` tinyint(4) DEFAULT 0 COMMENT '是否父子单（0父单，1子单）',
  `trade_code` varchar(200) DEFAULT NULL COMMENT '交易编号',
  `total` bigint(10) DEFAULT NULL COMMENT '总数量',
  `total_amount` decimal(11, 2) DEFAULT NULL COMMENT '总金额',
  `promotion_amount` decimal(11, 2) DEFAULT NULL COMMENT '折扣金额',
  `total_goods_price` decimal(11, 2) DEFAULT NULL COMMENT '商品实际总金额',
  `total_express_price` decimal(11, 2) DEFAULT NULL COMMENT '运费总金额',
  `total_price` decimal(11, 2) DEFAULT NULL COMMENT '实际支付价格(含税)',
  `create_time` datetime(0) NOT NULL COMMENT '下单时间',
  `create_datestamp` bigint(20) DEFAULT NULL COMMENT '下单时间戳',
  `payment_method` tinyint(4) DEFAULT -1 COMMENT '支付方式:1 支付宝 2 微信',
  `status` tinyint(11) DEFAULT NULL COMMENT '订单状态: 1未付款 2已取消 3 已付款待审核 4 审核失败 5待发货 6 待收货 7 待评价 8 交易完成',
  `payment_time` datetime(0) DEFAULT NULL COMMENT '付款时间',
  `payment_datestamp` bigint(20) DEFAULT NULL COMMENT '付款时间戳',
  `name` varchar(50) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `town` varchar(50) DEFAULT NULL COMMENT '镇',
  `address` varchar(254) DEFAULT NULL COMMENT '详细地址',
  `receipt_time` datetime(0) DEFAULT NULL COMMENT '收货时间',
  `receipt_datestamp` bigint(20) DEFAULT NULL COMMENT '收货时间戳',
  `receipt_remark` varchar(400) DEFAULT NULL COMMENT '收货备注',
  `delivery` varchar(100) DEFAULT NULL COMMENT '发货人',
  `delivery_time` datetime(0) DEFAULT NULL COMMENT '发货时间',
  `delivery_datestamp` bigint(20) DEFAULT NULL COMMENT '发货时间戳',
  `logistics_company` bigint(10) DEFAULT NULL COMMENT '物流公司',
  `logistics_name` varchar(50) DEFAULT NULL COMMENT '物流公司代码',
  `logistics_number` varchar(100) DEFAULT NULL COMMENT '物流单号',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux1_t_order`(`order_code`) USING BTREE,
  INDEX `ix1_t_order`(`user_id`) USING BTREE,
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_order_details`;
CREATE TABLE `t_order_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
   `order_code` varchar(150) DEFAULT NULL COMMENT '订单编号',
  `goods_item_id` bigint(20) DEFAULT NULL COMMENT '货号id',
  `goods_name` varchar(200)  DEFAULT NULL COMMENT '商品名称',
  `spec_value` varchar(500)  DEFAULT NULL COMMENT '规格值(所有的)',
  `goods_item_no` varchar(50)  DEFAULT NULL COMMENT 'sku货号',
  `goods_price` decimal(11, 2) DEFAULT NULL COMMENT '商品原价价格',
  `quantity` bigint(20) DEFAULT NULL COMMENT '数量',
  `preferential_price` decimal(11, 2) DEFAULT NULL COMMENT '优惠金额',
  `price` decimal(20, 2) DEFAULT NULL COMMENT '商品实际销售价格',
  `goods_item_cost_price` decimal(11, 2) DEFAULT NULL COMMENT '当时成本价',
  `show_status` tinyint(4) DEFAULT 0 COMMENT '显示状态0:正常 所有显示 1:删除状态,回收站显示2:删除,只在后台显示',
  `receiving_status` tinyint(4) DEFAULT 1 COMMENT '收货状态：1未收货，2收到货',
  `receiving_time` datetime(0) DEFAULT NULL COMMENT '收货时间',
  `receiving_timetamp` bigint(20) DEFAULT NULL COMMENT '收货时间戳',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix1_t_order_details`(`order_id`) USING BTREE,
  INDEX `ix2_t_order_details`(`order_code`) USING BTREE,
  INDEX `ix3_t_order_details`(`goods_item_id`) USING BTREE,
  INDEX `ix4_t_order_details`(`goods_item_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;



