package com.ryan.bit.nio.channel.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/11/1
 * @since 1.0.0
 */
public class SocketReadHandler implements Runnable {

    private SocketChannel socketChannel;

    public SocketReadHandler(Selector selector, SocketChannel socketChannel) throws IOException{
        this.socketChannel = socketChannel;

        socketChannel.configureBlocking(false);

        SelectionKey selectionKey = socketChannel.register(selector, 0);

        //将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。
        //参看dispatch(SelectionKey key)
        selectionKey.attach(this);

        //同时将SelectionKey标记为可读，以便读取。
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        try {
            socketChannel.read(buffer);

            //激活线程池 处理这些request
            //requestHandle(new Request(socket,btt));
        } catch (Exception ex) {

        }
    }
}
