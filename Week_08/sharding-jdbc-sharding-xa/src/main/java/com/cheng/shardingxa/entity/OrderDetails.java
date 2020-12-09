package com.cheng.shardingxa.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author Cheng Fengping
 * @since 2020-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 货号id
     */
    private Long goodsItemId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 规格值id
     */
    private Long specId;

    /**
     * 商品缩略图
     */
    private String img;

    /**
     * 商品编号
     */
    private String goodsItemNo;

    /**
     * 当时商品价格
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 当时成本价
     */
    private BigDecimal costPrice;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modTime;


}
