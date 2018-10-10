package org.jtm.utils;

import org.jtm.entity.Info;
import org.jtm.entity.Student;

/**
 * Java clone demo
 */
public class ObjectCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student();
        Info info = new Info();
        info.setId("111");
        s1.setInfo(info);
        s1.setName("zz");
        s1.setMemo("memo");

        Student student = s1.clone();
        student.setMemo("mmm");
        student.getInfo().setId("222");

        System.out.println(s1.getInfo().getId());
        System.out.println(s1.getName());
        System.out.println(s1.getMemo());

    }
}
