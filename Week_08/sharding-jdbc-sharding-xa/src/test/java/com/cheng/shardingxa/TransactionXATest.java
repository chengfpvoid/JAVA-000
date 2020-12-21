package com.cheng.shardingxa;

import com.cheng.shardingxa.entity.Inventory;
import com.cheng.shardingxa.entity.Order;
import com.cheng.shardingxa.mapper.InventoryMapper;
import com.cheng.shardingxa.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionXATest {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryMapper inventoryMapper;

    @ShardingTransactionType(value = TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void testCreateOrderRollback() {
        createOrder();

        throw new RuntimeException();
    }

    @ShardingTransactionType(value = TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void testCreateOrder() {
        createOrder();
    }

    private void createOrder() {
        Inventory inventory = inventoryMapper.selectById(1L);
        inventory.setNum(1).setVersion(inventory.getVersion() + 1);
        inventoryMapper.updateById(inventory);

        Order order = new Order();
        order.setOrderId(100000L);
        order.setUserId(1L);
        order.setTotalPrice(BigDecimal.ONE);
        orderMapper.insert(order);
    }

}
