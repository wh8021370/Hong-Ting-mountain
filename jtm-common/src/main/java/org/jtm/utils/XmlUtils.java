/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>org.jtm.utils<br/>
 * <b>文件名：</b>XmlUtils.java<br/>
 * <b>日  期：</b>2018/9/19<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package org.jtm.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>类  名：</b>XmlUtils<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Administrator<br/>
 * <b>创建时间：</b>2018/9/19<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class XmlUtils {

    public static void main(String[] args) {

        String xml = "<xmlData>\n" +
                "  <taskId>1272522</taskId>\n" +
                "  <relcurrentnode/>\n" +
                "  <bh>TCR-v1-2018</bh>\n" +
                "  <gqybgtzh/>\n" +
                "  <tcrbh>TCR-GM-20180918-0602</tcrbh>\n" +
                "  <processApplicant>11</processApplicant>\n" +
                "  <dh>18130621222</dh>\n" +
                "  <modelCode/>\n" +
                "  <lcfqsj>2018-09-18</lcfqsj>\n" +
                "  <processApplyDept>2</processApplyDept>\n" +
                "  <bgjd>XMJD</bgjd>\n" +
                "  <yylx>01</yylx>\n" +
                "  <wtyzx/>\n" +
                "  <wtlx>ysdmbg</wtlx>\n" +
                "  <sfyxshj>Y</sfyxshj>\n" +
                "  <emergencyDegree>emergency</emergencyDegree>\n" +
                "  <wtms>fds</wtms>\n" +
                "  <yyfx>df</yyfx>\n" +
                "  <jyggfa>dsf</jyggfa>\n" +
                "  <fileLocation/>\n" +
                "  <sjbgrwzp index=\"0\">\n" +
                "    <date/>\n" +
                "    <isAgree/>\n" +
                "    <xm>11</xm>\n" +
                "    <backDate/>\n" +
                "    <remark/>\n" +
                "    <bm>2</bm>\n" +
                "    <bm_name>测试部</bm_name>\n" +
                "    <xm_name>小五</xm_name>\n" +
                "    <opinion/>\n" +
                "  </sjbgrwzp>\n" +
                "  <kjsqsp index=\"1\">\n" +
                "    <date/>\n" +
                "    <isAgree/>\n" +
                "    <xm>9</xm>\n" +
                "    <remark/>\n" +
                "    <bm>2</bm>\n" +
                "    <bm_name>测试部</bm_name>\n" +
                "    <xm_name>朽木</xm_name>\n" +
                "    <opinion/>\n" +
                "  </kjsqsp>\n" +
                "  <kjsqsp index=\"0\">\n" +
                "    <date>2018-09-18</date>\n" +
                "    <isAgree>R</isAgree>\n" +
                "    <xm>11</xm>\n" +
                "    <remark>d</remark>\n" +
                "    <bm>2</bm>\n" +
                "    <bm_name>测试部</bm_name>\n" +
                "    <xm_name>小五</xm_name>\n" +
                "    <opinion>ds</opinion>\n" +
                "  </kjsqsp>\n" +
                "</xmlData>";
        List<Map<String,String>> reultMap =  getMapFromXmlData(xml);
    }
    /**
     * 根据xmlData 解析数据
     *
     * @param xmlData xmlData
     * @return 解析后的数据
     */
    static List<Map<String, String>> getMapFromXmlData(String xmlData) {
        List<Map<String, String>> mapList = new ArrayList<>();
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlData);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (document != null) {
            // 得到根节点下的节点列表
            List<Element> list = document.getRootElement().elements();
            // 遍历二级节点
            for (Element ele : list) {
                Map<String, String> map = new HashMap<>();
                // 得到二级节点下的节点列表
                List<Element> elements = ele.elements();
                for (Element element:elements){
                    map.put(element.getName(),element.getStringValue());
                }
                mapList.add(map);
            }
        }
        return mapList;
    }
}
