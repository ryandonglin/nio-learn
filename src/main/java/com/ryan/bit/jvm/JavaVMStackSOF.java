package com.ryan.bit.jvm;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/11
 * @since 1.0.0
 */
public class JavaVMStackSOF {

    private int stackLength = -1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    /**
     * JVM args: -Xss128k
     *
     * @param args
     */
    public static void main(String[] args) throws Throwable {

        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length : " + oom.stackLength);
            throw e;
        }
    }
}
