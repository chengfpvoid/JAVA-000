package com.cheng.java000;

import com.cheng.java000.loader.HelloClzLoader;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Hello.xlass");
        try {
            byte[] bytes = IOUtils.readFully(inputStream,inputStream.available(),false);
            Class helloClass = new HelloClzLoader(bytes).loadClass("Hello");
            Object obj = helloClass.newInstance();
            Method method = helloClass.getMethod("hello",null);
            method.invoke(obj,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
