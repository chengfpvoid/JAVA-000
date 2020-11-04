## week03 - netty网关作业

> 作业要求
>
> 1. 周四作业整合你上次作业的httpclient/okhttp，调用后端服务，拿到后端服务真实的响应结果替换上次写死的Hello world.
>
> 3. 周六作业：实现过滤器, 实现一个Request Filter的过滤器，1 在filter中拿到http的请求头，2 给请求头添加新的key-value,key是nio,value是你的名字，实际请求后端服务的时候，把这些请求头都拿出来，添加到对后端的请求头中。

网关代码作业链接：https://github.com/chengfpvoid/JavaCourseCodes/tree/main/02nio

后端服务代码链接：https://github.com/chengfpvoid/JavaCourseCodes/tree/main/02nio/backend-demo

### 调试情况

先开启后端代码的服务 直接访问 http://localhost:8088/hello  未拼接任何参数，返回 Hello null。

启动网关服务之后 访问http://127.0.0.1:8888/hello 会实际访问到后端服务http://localhost:8088/hello

同样未拼接任何参数返回 Hello chengfpvoid 。chengfpvoid这个值来自网关加入的过滤器，添加了key是nio 值是chengfpvoid的请求头，后端服务拿到请求头之后获取nio的值去拼接。

后端实现逻辑如下：

```java
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(required = false) String name, HttpServletRequest request) {
        String defaultName = request.getHeader("nio");
        if(StringUtils.isEmpty(name)) {
            name = defaultName;
        }
        System.out.println("execute hello method:"+ count.incrementAndGet() + "counts");
        return "Hello " + name;
    }
```



### 网关代码逻辑

#### 1 ：网关代码中整合okhttp 请求实际的后端服务

新建一个OkhttpOutboundHandler的类，构造函数接收到要代理访问的后端服务的地址，初始化线程池和okHttpClient

```java
  public OkhttpOutboundHandler(String backendUrl) {
      ...

    }
```

定义handle方法，使用线程池异步调用get请求后端服务。

```java
 public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        final String url = backendUrl + fullRequest.uri();
        proxyService.submit(() -> doGet(fullRequest,ctx,url));
    }
```

doGet方法中使用okHttpClient异步调用后端真实的服务url,并在ChannelHandlerContext写入响应结果。

HttpInboundHandler的channelRead方法中 调用handle方法，调用真实的后端请求。

#### 2 给网关增加Http Request Filter的实现

构建链式过滤器，代码参见FilterChain，HttpRequestFilterChain，HttpRequestFilter相关类和接口。

定义NioKeyRequestFilter实现HttpRequestFilter接口，实现作业要求的逻辑：给request header添加nio的key和value。

```java
public class NioKeyRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpRequestFilterChain chain) {
        HttpHeaders httpHeaders = fullRequest.headers();
        httpHeaders.add("nio","chengfpvoid");
        chain.doFilter(fullRequest,ctx);
    }
}
```



HttpInboundHandler的channelRead方法中调用handle方法之前添加链式过滤器的调用。

```java
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //链式模式添加过滤器
            HttpRequestFilterChain chain = new HttpRequestFilterChain();
            chain.addFilter(new NioKeyRequestFilter());
            chain.doFilter(fullRequest,ctx);
            //okhttp3 handle
            okHandler.handle(fullRequest,ctx);
           .... 
```

####  3 okHttp请求头中封装当前所有的请求头

```java
        HttpHeaders httpHeaders = fullRequest.headers();
        Request.Builder builder = new Request.Builder()
                .url(url).addHeader("Content-Type", "application/json");
        
        httpHeaders.forEach(entry-> builder.addHeader(entry.getKey(),entry.getValue()));
        Request request = builder.build();
....
```

