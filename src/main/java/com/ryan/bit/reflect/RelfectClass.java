package com.ryan.bit.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/19
 * @since 1.0.0
 */
public class RelfectClass {

    public static void main(String[] args) {

        String className = "com.ryan.bit.reflect.TestClass";
        try {
            Class clz = Class.forName(className);
            TestClass testClass = (TestClass)clz.newInstance();
            Field field = clz.getField("strValue");
            field.set(testClass, "Hello world, this is reflect!");
            System.out.println(field.get(testClass));

            Method method = clz.getMethod("setStrValue", String.class);
            method.invoke(testClass,"HelloWorld! Invoke method!");
            Method outputMethod = clz.getMethod("getStrValue", null);
            System.out.println(outputMethod.invoke(testClass, null));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
