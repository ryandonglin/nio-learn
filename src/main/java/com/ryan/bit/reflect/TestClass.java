package com.ryan.bit.reflect;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/19
 * @since 1.0.0
 */
public class TestClass {

    public String strValue;

    public Integer intValue;

    public Double doubleValue;

    public Character charValue;

    public Long longValue;


    public TestClass() {
    }

    public TestClass(String strValue, Integer intValue, Double doubleValue, Character charValue, Long longValue) {
        this.strValue = strValue;
        this.intValue = intValue;
        this.doubleValue = doubleValue;
        this.charValue = charValue;
        this.longValue = longValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Character getCharValue() {
        return charValue;
    }

    public void setCharValue(Character charValue) {
        this.charValue = charValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }
}
