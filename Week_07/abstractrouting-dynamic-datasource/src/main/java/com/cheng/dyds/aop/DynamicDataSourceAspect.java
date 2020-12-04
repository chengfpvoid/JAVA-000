package com.cheng.dyds.aop;

import com.cheng.dyds.annotation.Ds;
import com.cheng.dyds.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, Ds ds) throws Throwable {
        String key = ds.value();
        if (DynamicDataSourceContextHolder.DATASOURCE_KEYS.contains(key)) {
            log.debug("Use DataSource: {} > {}", key, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey(key);
        } else {
            log.info("数据源[{}]不存在，使用默认数据源 > {}", key, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey(DynamicDataSourceContextHolder.DEFAULT_ROUTING_KEY);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, Ds ds) {
        log.debug("Revert DataSource : " + ds.value() + " > " + point.getSignature());
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();

    }
}
