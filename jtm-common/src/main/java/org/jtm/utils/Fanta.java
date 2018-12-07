
package org.jtm.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Fanta {
    public static void main(String[] args) {
        System.out.print(test("(YY)||((JN01||(DY02||AA)||ZZ))&&(CC||DD)"));
    }

    public static String test(String exp) {
        exp = exp.replace("", "");
        Stack<Character> characterStack = new Stack<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder cache = new StringBuilder();
        List<String> result = new ArrayList<>();
        List<String> all = new ArrayList<>();
        StringBuilder err = new StringBuilder();
        char[] expArr = exp.toCharArray();
        for (int i = 0; i < expArr.length; i++) {
            char c = expArr[i];
            if (i == 0) {
                if (c == '|' || c == '&' || c == ')') {
                    err.append("起始位置出错！");
                } else if (c == '(') {
                    characterStack.push(c);
                } else {
                    cache.append(c);
                }
            } else {
                char p = expArr[i - 1];
                if (c == '|' || c == '&') {
                    if (p == '(') {
                        err.append("左括号直接跟运算符~！");
                    }
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (stack.pop() != c) {
                            err.append("云算符不匹配！").append(i);
                            stack.clear();
                        }
                        result.add(cache.toString());
                        cache = new StringBuilder();
                    }
                } else if (c == '(') {
                    if (!isOperator(p) && !isLeft(p)) {
                        err.append("缺失运算连接符");
                    }
                    if (!stack.empty()) {
                        err.append("错误的运算符！").append(i).append(stack.get(0));
                        stack.clear();
                        result.add(cache.toString());
                        cache = new StringBuilder();
                    }
                    characterStack.push(c);
                } else if (c == ')') {
                    if (isOperator(p) && cache.length() < 1) {
                        err.append("运算符后缺失内容！");
                    }
                    if (!stack.empty()) {
                        err.append("错误的运算符！").append(i).append(stack.get(0));
                        stack.clear();
                    }
                    if (!characterStack.empty()) {
                        characterStack.pop();
                    } else {
                        err.append("右括号找不到匹配的左括号").append(i + 1);
                    }
                    if (result.size() < 2) {
                        err.append("组合内部缺失运算符").append(i);
                    }
                    all.addAll(result);
                    result.clear();
                } else {
                    if (!stack.empty()) {
                        err.append("错误的运算符！").append(i).append(stack.get(0));
                        stack.clear();
                        result.add(cache.toString());
                        cache = new StringBuilder();
                    }
                    cache.append(c);
                }
            }
        }
        if (cache.length() > 0) {
            result.add(cache.toString());
        }
        if (!characterStack.empty()) {
            err.append("左右括号不匹配");
        }
        for (String s : all) {
            System.out.println(s);
        }
        return err.toString();
    }

    private static boolean isOperator(char c) {
        return c == '|' || c == '&';
    }

    private static boolean isLeft(char c) {
        return c == '(';
    }

    private static boolean isRight(char c) {
        return c == ')';
    }
}
