/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>com.paradise.common<br/>
 * <b>文件名：</b>Demo2.java<br/>
 * <b>日  期：</b>2018/11/20<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package com.paradise.common;

import java.math.BigDecimal;

/**
 * <b>类  名：</b>Demo2<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/11/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class Demo2 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(getRandom(1));
        System.out.println(new BigDecimal(System.currentTimeMillis() + getRandom(7)));
    }

    private static void print(Object o) {
        System.out.println(o);
    }

    private static String getRandom(Integer length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double d = Math.floor(Math.random() * 10);
            stringBuilder.append(String.valueOf((int)d));
        }
        return stringBuilder.toString();
    }
}
