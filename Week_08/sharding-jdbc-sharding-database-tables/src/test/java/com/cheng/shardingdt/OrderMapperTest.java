package com.cheng.shardingdt;

import com.cheng.shardingdt.entity.Order;
import com.cheng.shardingdt.entity.OrderDetails;
import com.cheng.shardingdt.mapper.OrderDetailsMapper;
import com.cheng.shardingdt.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Test
    //@Transactional
    public void testInsertOrder() {
        for(int i = 0 ; i < 16; i++) {
            Order order = new Order();
            order.setOrderId(i+1000L);
            order.setUserId((long) i);
            order.setTotalPrice(BigDecimal.ONE);
            orderMapper.insert(order);
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

            orderDetailsMapper.insert(orderDetails);
        }

    }

}
