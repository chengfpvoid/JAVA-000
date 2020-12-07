DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `tb_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `total_price` decimal(11, 2) NULL DEFAULT NULL COMMENT '实际支付价格',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `create_date_stamp` bigint(20) NULL DEFAULT NULL COMMENT '下单时间戳',
  `payment_method` tinyint(4) NULL DEFAULT -1 COMMENT '支付方式 1 微信 2 支付宝',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态: 1未付款 2 已取消 3 待审核  4 待发货 5 待收货 6 待评价 7 交易完成',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `payment_date_stamp` bigint(20) NULL DEFAULT NULL COMMENT '付款时间戳',
  `shipping_id` bigint(20) NULL NULL COMMENT '寄送信息id',
  `receipt_time` datetime  DEFAULT NULL COMMENT '收货时间',
  `receipt_date_stamp` bigint(20) DEFAULT NULL COMMENT '收货时间戳',
  `delivery_time` datetime  DEFAULT NULL COMMENT '发货时间',
  `delivery_date_stamp` bigint(20)  DEFAULT NULL COMMENT '发货时间戳',
  `trade_code` varchar(255)  DEFAULT NULL COMMENT '交易流水号',
  `audit_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态 1审核通过 2不通过',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `audit_timestamp` bigint(20) NULL DEFAULT NULL COMMENT '审核时间戳',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `cancel_time` datetime  DEFAULT NULL COMMENT '取消时间',
  `cancel_remark` varchar(255)  DEFAULT NULL COMMENT '取消原因',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间（如果用户取消订单则存取消时间）',
  `finish_date_stamp` bigint(20) NULL DEFAULT NULL COMMENT '完成时间（如果用户取消订单则存取消时间）戳',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_order_code`(`order_code`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表';

DROP TABLE IF EXISTS `t_order_details`;
CREATE TABLE `tb_order_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `goods_item_id` bigint(20)  NOT NULL COMMENT '货号id',
  `goods_name` varchar(32) NOT NULL COMMENT '商品名称',
  `spec_id` bigint(20) NOT NULL COMMENT '规格值id',
  `img` varchar(255) NOT NULL COMMENT '商品缩略图',
  `goods_item_no` varchar(50) NOT NULL COMMENT '商品编号',
  `price` decimal(11, 2) NOT NULL COMMENT '当时商品价格',
  `quantity` int(10) NOT NULL COMMENT '数量',
  `cost_price` decimal(11, 2) NULL DEFAULT NULL COMMENT '当时成本价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `mod_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情表';

