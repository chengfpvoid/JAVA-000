package com.cheng.shardingdt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Cheng Fengping
 * @since 2020-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 实际支付价格
     */
    private BigDecimal totalPrice;

    /**
     * 下单时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 下单时间戳
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createDateStamp;

    /**
     * 支付方式 1 微信 2 支付宝
     */
    private Integer paymentMethod;

    /**
     * 订单状态: 1未付款 2 已取消 3 待审核  4 待发货 5 待收货 6 待评价 7 交易完成
     */
    private Integer status;

    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;

    /**
     * 付款时间戳
     */
    private Long paymentDateStamp;

    /**
     * 寄送信息id
     */
    private Long shippingId;

    /**
     * 收货时间
     */
    private LocalDateTime receiptTime;

    /**
     * 收货时间戳
     */
    private Long receiptDateStamp;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 发货时间戳
     */
    private Long deliveryDateStamp;

    /**
     * 交易流水号
     */
    private String tradeCode;

    /**
     * 审核状态 1审核通过 2不通过
     */
    private Integer auditStatus;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核时间戳
     */
    private Long auditTimestamp;

    /**
     * 审核备注
     */
    private String auditRemark;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 取消原因
     */
    private String cancelRemark;

    /**
     * 完成时间（如果用户取消订单则存取消时间）
     */
    private LocalDateTime finishTime;

    /**
     * 完成时间（如果用户取消订单则存取消时间）戳
     */
    private Long finishDateStamp;


}
