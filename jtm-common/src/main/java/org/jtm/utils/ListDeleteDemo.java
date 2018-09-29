package org.jtm.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：如果在遍历列表使用remove方法移除列表中的元素，循环会继续进行吗
 * 答案是否定的
 * @author paradise 2018.09.11
 */
public class ListDeleteDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("000");
        list.add("001");
        list.add("002");
        list.add("003");
        list.add("004");
        list.add("005");
        delete(list);
    }

   private static void delete(List<String> list) {
        for (String str : list) {
            if (str.equals("003")){
                list.remove(str);
            }
            System.out.println(str);
        }
    }
}
