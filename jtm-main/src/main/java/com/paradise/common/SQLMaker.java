/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>com.paradise.common<br/>
 * <b>文件名：</b>SQLMaker.java<br/>
 * <b>日  期：</b>2018/11/27<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package com.paradise.common;

import java.util.UUID;

/**
 * <b>类  名：</b>SQLMaker<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/11/27<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class SQLMaker {
    public static void main(String[] args) {
        System.out.println(sqlMaker("MODEL_CODE = ? AND PCO = ?", "123", "QQQ"));

        String[] arr = {"ABC","EFG"};
        System.out.println(strMaker(arr,"static111","static"));
        String uuid= UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.length());
        UUID uuid1 = new UUID(System.currentTimeMillis(),200L);
        System.out.println(UUID.fromString(uuid1.toString()));
    }


    private static String sqlMaker(String sql, String... para) {
        for (String p : para) {
            p = "'" + p + "'";
            sql = sql.replaceFirst("\\?", p);
        }
        return sql;
    }

    private static String strMaker(String[] arr,String ...param){
        if (arr.length != param.length){
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]).append("=").append(param[i]).append(",");
        }
        return result.toString().substring(0,result.length()-1);
    }
}
