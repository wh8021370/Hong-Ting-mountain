/**
 * <b>工程名：</b>ZhangDong<br/>
 * <b>包  名：</b>com.paradise.common<br/>
 * <b>文件名：</b>StringAbt.java<br/>
 * <b>日  期：</b>2018/8/2<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package com.paradise.common;

/**
 * <b>类  名：</b>StringAbt<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/8/2<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class StringAbt {
    public static void main(String[] args) {
        String js = " html += 'id=\""+ getName() + "\" + \"_\" + i />'";

        String  x  = "<input  type=\"radio\" id=\"" + getName() + "\" + i + '\"  ";
        System.out.println(x);
    }

    public static String getName(){
        return "name_";
    }
}
