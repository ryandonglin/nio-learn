package com.ryan.bit.nio.channel.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/10/20
 * @since 1.0.0
 */
public class ChannelTest {

    public ChannelTest() {

    }

    public void startServer() throws IOException {
        int channels = 0;
        int nKeys = 0;
        int currentSelector = 0;

        // 使用selector
        Selector selector = Selector.open();

        // 建立channel并绑定到9000 端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 9000);
        ssc.socket().bind(inetSocketAddress);

        // 使设定non-blocking方式
        ssc.configureBlocking(false);

        // 向selector注册Channel及我们感兴趣的事件
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        printKeyInfo(selectionKey);

        // 不间断轮询
        while (true) {
            debug("NIO Selector Test: Start selecting");

            // selector 通过select方法通知我们我们感兴趣的事情发生了
            nKeys = selector.select();
            // 如果有我们注册的时间发生了，他的传回值就会大于0
            if (0 < nKeys) {
                debug("Selector test: Number of keys after select operation: " +nKeys);
                // selector 传回一组selectionKeys
                // 我们从这些key中的channel()方法中获得我们刚刚注册的channel
                Set selectionKeys = selector.keys();
                Iterator ite = selectionKeys.iterator();
                while (ite.hasNext()) {
                    selectionKey = (SelectionKey)ite.next();
                    printKeyInfo(selectionKey);

                    // 一个key被处理完成后，就都被从就绪关键字(read keys)列表中出去
                    ite.remove();

                    if (selectionKey.isAcceptable()) {
                        // 从channel()中获取我们刚刚注册的channel
                        ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();

                        SocketChannel sc = channel.socket().accept().getChannel();

                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        debug( ++channels + "");
                    } else {
                        debug("Selector : Channel is not acceptable");
                    }
                }
            }
        }

    }

    public static void printKeyInfo(SelectionKey sk) {
        String s = new String();
        s += "Att: " + (sk.attachment() == null? "no": "yes");
        s += ",Read: " + sk.isReadable();
        s += ",Cnct: " + sk.isConnectable();
        s += ",Wrt: " + sk.isWritable();
        s += ",Valid: " + sk.isValid();
        s += ",Ops: " + sk.interestOps();
        System.out.println(s);
    }

    public static void debug (String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        ChannelTest channelTest = new ChannelTest();
        try {
            channelTest.startServer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
