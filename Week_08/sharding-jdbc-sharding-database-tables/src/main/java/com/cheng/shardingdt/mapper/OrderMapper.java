package com.cheng.shardingdt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.shardingdt.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Cheng Fengping
 * @since 2020-12-08
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
