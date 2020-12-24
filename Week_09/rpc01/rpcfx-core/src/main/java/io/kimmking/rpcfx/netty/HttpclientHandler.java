package io.kimmking.rpcfx.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpclientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        String content = msg.content().toString(StandardCharsets.UTF_8);
        log.info("nett http client response content:{}",content);
        Promise<String> promise = ctx.channel().attr(HttpClient.NETTY_RESPONSE_PROMISE_KEY).get();
        promise.setSuccess(content);

    }
}
