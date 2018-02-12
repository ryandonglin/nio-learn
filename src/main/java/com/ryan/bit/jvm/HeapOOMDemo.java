package com.ryan.bit.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/11
 * @since 1.0.0
 */
public class HeapOOMDemo {

    /**
     * VM args: -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {

        List<Object> list = new ArrayList();

        while (true) {
            list.add(new Object());
        }
    }
}
