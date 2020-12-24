package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.utils.NamedThreadFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpClient {
    public static final AttributeKey<Promise<String>> NETTY_RESPONSE_PROMISE_KEY = AttributeKey.valueOf("netty-client-response-promise");

    public String connect(String url, String content, long timeOutMills) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Promise<String> responsePromise =  new DefaultEventLoop(null, new NamedThreadFactory("NettyResponsePromiseNotify"))
                .newPromise();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .attr(NETTY_RESPONSE_PROMISE_KEY,responsePromise)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new HttpResponseDecoder())
                                    .addLast(new HttpRequestEncoder())
                                    .addLast(new HttpclientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect();
            URI uri = new URI(url);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
            request.headers().set(HttpHeaderNames.HOST,uri.getHost());
            request.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
            channelFuture.channel().writeAndFlush(request);
        } finally {
            group.shutdownGracefully();

        }
        return get(responsePromise,timeOutMills);

    }

    private <V> V get(Promise<V> future, long timeOutMills) {
        if (!future.isDone()) {
            CountDownLatch l = new CountDownLatch(1);
            future.addListener(future1 -> {
                log.info("received response,listener is invoked");
                if (future1.isDone()) {
                    // io线程会回调该listener
                    l.countDown();
                }
            });

            boolean interrupted = false;
            if (!future.isDone()) {
                try {
                    l.await(timeOutMills, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    log.error("e:{}", e);
                    interrupted = true;
                }

            }

            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

        if (future.isSuccess()) {
            return future.getNow();
        }
        log.error("wait result time out ");
        return null;
    }
}
