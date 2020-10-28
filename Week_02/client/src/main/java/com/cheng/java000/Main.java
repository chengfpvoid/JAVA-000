package com.cheng.java000;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801
 */
public class Main {

    public static void main(String[] args) {
        String url = "http://localhost:8801";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String res = response.body().string();
            System.out.println("请求url:"+url + "响应结果是："+res);
        } catch (IOException e) {
            System.out.println("okttp3同步请求异常："+ e.getMessage());
        }

    }
}
