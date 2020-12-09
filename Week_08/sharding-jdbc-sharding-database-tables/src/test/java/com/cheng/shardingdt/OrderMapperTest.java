package com.cheng.shardingdt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheng.shardingdt.entity.Order;
import com.cheng.shardingdt.entity.OrderDetails;
import com.cheng.shardingdt.mapper.OrderDetailsMapper;
import com.cheng.shardingdt.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Test
    public void testInsertOrder() {
        for(int i = 0 ; i < 32; i++) {
            Order order = new Order();
            order.setOrderId(i+1000L);
           // order.setOrderId(snowflake.nextId());
            order.setUserId((long) i);
            order.setTotalPrice(BigDecimal.ONE);
            int r = orderMapper.insert(order);
            Assertions.assertTrue(r > 0);
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(order.getOrderId())
                    .setGoodsItemId(1L)
                    .setGoodsItemNo("1")
                    .setGoodsName("测试商品")
                    .setImg("图片")
                    .setSpecId(1L)
                    .setPrice(BigDecimal.ONE)
                    .setQuantity(1)
                    .setCostPrice(BigDecimal.ONE);

            int r1 =  orderDetailsMapper.insert(orderDetails);
            Assertions.assertTrue(r1 > 0);

        }
    }

    @Test
    public void testQueryOrder() {
        //分库分表规则都弄上，最终路由到指定库的指定表 只查询一次
        orderMapper.selectOne(new QueryWrapper<Order>()
                .lambda().eq(Order::getUserId,1).eq(Order::getOrderId,1001L));
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setOrderId(1015L);
        order.setPaymentMethod(2).setStatus(4)
                .setPaymentTime(LocalDateTime.now())
                .setAuditTimestamp(System.currentTimeMillis());
        orderMapper.updateById(order);
    }

}
