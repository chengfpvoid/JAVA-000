package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.exception.RpcfxException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        try {
            Class<?> clz =  Class.forName(serviceClass);
            return this.applicationContext.getBean(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RpcfxException("未发现服务类："+ serviceClass +"的实现");
        }
    }
}
