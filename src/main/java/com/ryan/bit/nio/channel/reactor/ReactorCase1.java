package com.ryan.bit.nio.channel.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/11/1
 * @since 1.0.0
 */
public class ReactorCase1 implements Runnable {

    public final Selector selector;

    public final ServerSocketChannel serverSocketChannel;

    public ReactorCase1(int port) throws IOException {
        selector=Selector.open();
        serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress(InetAddress.getLocalHost(),port);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);

        //向selector注册该channel
        SelectionKey selectionKey=serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //利用selectionKey的attache功能绑定Acceptor 如果有事情，触发Acceptor
        selectionKey.attach(new Acceptor(this));
    }


    public void run() {
        try{
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> ite = keys.iterator();
                //Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。
                while (ite.hasNext()) {
                    //来一个事件 第一次触发一个accepter线程
                    //以后触发SocketReadHandler
                    SelectionKey key = ite.next();
                    dispatch(key);
                    keys.remove(key);
                }
            }
        } catch (Exception ex) {

        }
    }

    // 运行Acceptor或 SocketReadHandler
    public void dispatch(SelectionKey key) {
        Runnable r = (Runnable) (key.attachment());
        if (null != r) {
            r.run();
        }
    }
}
