package com.cheng.dyds.config;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DynamicDataSourceContextHolder {

    public static final String DEFAULT_ROUTING_KEY = "master";

    private static ThreadLocal<String> KEY_HOLDER = new ThreadLocal<>();

    public static List<String> DATASOURCE_KEYS = new ArrayList<>();

    public static String getDataSourceRouterKey() {
        return KEY_HOLDER.get();
    }

    public static void setDataSourceRouterKey(String dataSourceRouterKey) {
        log.info("切换至{}数据源", dataSourceRouterKey);
        KEY_HOLDER.set(dataSourceRouterKey);
    }

    public static void removeDataSourceRouterKey() {
        KEY_HOLDER.remove();
    }


}
