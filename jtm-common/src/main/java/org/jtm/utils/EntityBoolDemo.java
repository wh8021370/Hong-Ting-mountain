package org.jtm.utils;

/**
 * 测试实体类初始化boolean值
 */
public class EntityBoolDemo {
    public static void main(String[] args) {
        Entity entity = new Entity("id", "name");
        if (entity.isFlag()) {
            System.out.println("||||||||||||||");
        } else {
            System.out.println(">>>>>>>>>>>>>>");
            System.out.println(entity.isFlag());
        }
    }
}
