package com.ryan.bit.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/9/14
 * @since 1.0.0
 */
public class Test1 {

    public static void main(String[] args) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            RandomAccessFile randomAccessFile = new RandomAccessFile(path + "test.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            // set the capacity of Buffer 48 characters
            ByteBuffer buf = ByteBuffer.allocate(48);
            int offset = fileChannel.read(buf); // read into buffer
            while (offset != -1) {
                System.out.println("Read : " + offset);
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println((char) buf.get());
                }
                buf.clear();
                offset = fileChannel.read(buf);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
