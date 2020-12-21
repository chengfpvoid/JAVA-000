package com.cheng.shardingms;

import com.cheng.shardingms.entity.User;
import com.cheng.shardingms.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void save() {
        User user = new User();
        user.setName("user1");
        user.setSex(0);
        user.setAge(18);
        Assertions.assertEquals(1, userMapper.save(user));
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setName("update");
        // 返回插入的记录数 ，期望是1条 如果实际不是一条则抛出异常
        Assertions.assertEquals(1,userMapper.update(user));
    }


    @Test
    public void selectById() {
        User user = userMapper.selectById(1L);
        log.info("result: {}", user);
    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectAll();
        log.info("result: {}", users);
    }


}
