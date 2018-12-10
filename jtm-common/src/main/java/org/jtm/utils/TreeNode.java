/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>org.jtm.utils<br/>
 * <b>文件名：</b>TreeNode.java<br/>
 * <b>日  期：</b>2018/12/9<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package org.jtm.utils;

import sun.reflect.generics.tree.Tree;

import java.util.List;

/**
 * <b>类  名：</b>TreeNode<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/12/9<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> childList;
    private String id;
    private String operator;
    private String exp;

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
