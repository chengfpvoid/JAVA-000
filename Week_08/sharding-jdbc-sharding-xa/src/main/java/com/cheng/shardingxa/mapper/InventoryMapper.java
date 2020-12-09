package com.cheng.shardingxa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.shardingxa.entity.Inventory;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品库存信息表 Mapper 接口
 * </p>
 *
 * @author Cheng Fengping
 * @since 2020-12-09
 */
@Repository
public interface InventoryMapper extends BaseMapper<Inventory> {

}
