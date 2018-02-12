package com.ryan.bit.nio.channel.reactor;

import java.nio.channels.SocketChannel;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/11/1
 * @since 1.0.0
 */
public class Acceptor implements Runnable {

    private  ReactorCase1 reactorCase1;

    public Acceptor(ReactorCase1 reactorCase1) {
        this.reactorCase1 = reactorCase1;
    }

    public void run() {

        try {
            SocketChannel socketChannel =reactorCase1.serverSocketChannel.accept();
            if (null != socketChannel) {
                new SocketReadHandler(reactorCase1.selector, socketChannel);
            }

        } catch (Exception ex) {

        }
    }
}
