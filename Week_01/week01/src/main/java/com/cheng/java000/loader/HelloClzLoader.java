package com.cheng.java000.loader;

/**
 * Hello.xlass文件类加载器
 */
public class HelloClzLoader  extends ClassLoader {

    private byte[] bytes;

    public HelloClzLoader(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
     * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(bytes == null) {
            throw new ClassNotFoundException();
        }
        byte[] classContent = new byte[bytes.length];
        for(int i = 0; i < bytes.length; i++) {
            classContent[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name,classContent,0,classContent.length);
    }
}
