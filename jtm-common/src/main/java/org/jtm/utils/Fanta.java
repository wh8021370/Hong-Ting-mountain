
package org.jtm.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class Fanta {
    public static void main(String[] args) {
        System.out.print(test("(YY)||((JN01||(DY02||AA)||ZZ))&&(CC||DD)"));
    }

    public static String test(String exp) {
        exp = exp.replace("", "");
        Stack<Character> characterStack = new Stack<>();
        Stack<Character> stack = new Stack<>();
        int level = 1;
        List<TreeNode> resultTree = new ArrayList<>();
        TreeNode root = new TreeNode();
        root.setId("root");
        root.setExp("");
        root.setChildList(new ArrayList<>());

        resultTree.add(root);

        TreeNode treeNode = root;

        StringBuilder cache = new StringBuilder();

        StringBuilder err = new StringBuilder();

        char[] expArr = exp.toCharArray();
        for (int i = 0; i < expArr.length; i++) {
            char c = expArr[i];
            if (i == 0) {
                if (isOperator(c) || isRight(c)) {
                    err.append("起始位置出错！");
                } else if (isLeft(c)) {
                    characterStack.push(c);
                    level++;
                    TreeNode node = new TreeNode();
                    node.setExp("(");
                    node.setId(UUID.randomUUID().toString());
                    node.setChildList(new ArrayList<>());
                    node.setParent(treeNode);
                    treeNode.getChildList().add(node);
                    treeNode = node;
                } else {
                    cache.append(c);
                }
            } else {
                char p = expArr[i - 1];
                if (isOperator(c)) {
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
                        if (!isRight(expArr[i - 2])) {
                            TreeNode node = new TreeNode();
                            node.setId(UUID.randomUUID().toString());
                            node.setParent(treeNode);
                            node.setExp(cache.toString());
                            treeNode.getChildList().add(node);
                        }
                        cache = new StringBuilder();
                    }
                } else if (isLeft(c)) {
                    if (!isOperator(p) && !isLeft(p)) {
                        err.append("缺失运算连接符");
                    }
                    if (!stack.empty()) {
                        err.append("错误的运算符！").append(i).append(stack.get(0));
                        stack.clear();
                        cache = new StringBuilder();
                    }
                    characterStack.push(c);
                    level++;
                    TreeNode node = new TreeNode();
                    node.setId(UUID.randomUUID().toString());
                    node.setExp("(");
                    node.setParent(treeNode);
                    node.setChildList(new ArrayList<>());
                    treeNode.getChildList().add(node);
                    treeNode = node;

                } else if (isRight(c)) {
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
                    level--;
                    if (cache.length() > 0) {
                        TreeNode node = new TreeNode();
                        node.setId(UUID.randomUUID().toString());
                        node.setParent(treeNode);
                        node.setExp(cache.toString());
                        treeNode.getChildList().add(node);
                        cache = new StringBuilder();
                    }
                    if (treeNode.getChildList().size() < 2) {
                        err.append("组合内部缺失运算符").append(i);
                    }
                    treeNode = treeNode.getParent();

                } else {
                    if (!stack.empty()) {
                        err.append("错误的运算符！").append(i).append(stack.get(0));
                        stack.clear();
                        cache = new StringBuilder();
                    }
                    cache.append(c);
                }
            }
        }

        if (!characterStack.empty()) {
            err.append("左右括号不匹配");
        }
        System.out.print(level);
        out(resultTree);
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

    private static void out(List<TreeNode> treeNodes) {
        for (TreeNode treeNode : treeNodes) {
            System.out.print(treeNode.getExp());
            if (treeNode.getChildList() != null && !treeNode.getChildList().isEmpty()) {
                out(treeNode.getChildList());
            }
        }
    }
}
