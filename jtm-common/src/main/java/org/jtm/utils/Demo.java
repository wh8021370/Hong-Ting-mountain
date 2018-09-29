/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>org.jtm.utils<br/>
 * <b>文件名：</b>Demo.java<br/>
 * <b>日  期：</b>2018/8/29<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package org.jtm.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>类  名：</b>Demo<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/8/29<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class Demo {

    private static int flag = 0;

    public static void main(String[] args) {
        String x = "a1 and a2 or (b1 or b2 and b3 and (c1 and c2)) and (a3 and a4)";
        System.out.println(stringToJson(x));
        String s1 = x.replaceAll("\\(", "( ").replaceAll("\\)", " )");
//        System.out.println(s1);
//        List<Object> objects = test(x);
//        for (Object o : objects) {
//            System.out.println(o);
//        }
    }

    /**
     * 转json字符串的尝试
     *
     * @param x
     * @return
     */
    public static String stringToJson(String x) {
        StringBuilder json = new StringBuilder();
        String s1 = x.replaceAll("\\(", "( ").replaceAll("\\)", " )");
        String[] arr = s1.split(" ");
        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            String op = "";
            if (i > 1) {
                if (arr[i - 1].equals("and") || arr[i - 1].equals("or")) {
                    op = arr[i - 1];
                } else {
                    op = arr[i - 2];
                }
            }
            if (item.equals("(")) {
                json.append("{\"key\" : \"root\",\"op\" : \"").append(op).append("\",\"child\" : [");
            } else if (item.equals("and") || item.equals("or")) {
            } else if (item.equals(")")) {
                json.append("],");
            } else {
                json.append("{ \"key\" : \"").append(arr[i]).append("\",\"op\" : \"").append(op).append("\"},");
            }
        }
        return json.toString();

    }

    public static List<Object> test(String x) {
        List<Object> resultList = new ArrayList<>();
        if (x.contains("(")) {
            int startIndex = x.indexOf("(");
            int lastIndex = getIndex(x);
            String sub = x.substring(startIndex + 1, lastIndex);
            String x1 = x.replace(x.substring(startIndex, lastIndex + 1), "[" + flag + "]");
            resultList.add(test(sub));
        } else {
            return toObj(x);
        }
        return resultList;
    }

    //    Map<String,String>
    //
    public static List<Object> toObj(String x) {
        List<Object> list = new ArrayList<>();
        // 根据空格解析成对象
        String[] arr = x.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("and") && !arr[i].equals("or")) {
                System.out.println(arr[i]);
                list.add(arr[i]);
                if (i > 1) {
                    System.out.println(arr[i - 1]);
                }
            }
        }
        return list;
    }

    /**
     * 获取对应第一个左括号的右括号的索引
     *
     * @param x
     * @return
     */
    public static int getIndex(String x) {
        String[] arr = x.split("");
        int f = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("(")) {
                f++;
            } else if (arr[i].equals(")")) {
                f--;
                if (f < 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
