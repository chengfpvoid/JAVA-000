package com.cheng.shardingxa.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * *
* 字段填充器
*
*/
@Component
@Slf4j
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if(log.isDebugEnabled()) {
            log.debug("start insert fill ....");
        }


        this.strictInsertFill(metaObject,"createTime",LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"createDateStamp",Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject,"modTime",LocalDateTime.class, LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(log.isDebugEnabled()) {
            log.debug("start update fill ....");
        }

        this.strictUpdateFill(metaObject,"modTime",LocalDateTime.class,LocalDateTime.now());

    }
}
