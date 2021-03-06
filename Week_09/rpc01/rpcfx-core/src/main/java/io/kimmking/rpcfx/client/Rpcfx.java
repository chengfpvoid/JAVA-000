package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) create(serviceClass, url, filter);

    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> AOP

        return (T) getEnhancer(serviceClass, url).create();

    }

    private static <T> Enhancer getEnhancer(Class<T> serviceClass, String url) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                RpcfxRequest request = new RpcfxRequest();
                request.setServiceClass(serviceClass.getName());
                request.setMethod(method.getName());
                request.setParams(objects);

                RpcfxResponse response = post(request, url);
                if (!response.isStatus()) {
                    throw response.getException();
                }
                return JSON.parse((String) response.getResult());

            }
            private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
                String reqJson = JSON.toJSONString(req);
                System.out.println("req json: "+reqJson);

                // 1.可以复用client
                // 2.尝试使用httpclient或者netty client
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(MediaType.get("application/json; charset=utf-8"), reqJson))
                        .build();
                String respJson = client.newCall(request).execute().body().string();
                System.out.println("resp json: "+respJson);
                return JSON.parseObject(respJson, RpcfxResponse.class);
            }
        });
        return enhancer;
    }

}
