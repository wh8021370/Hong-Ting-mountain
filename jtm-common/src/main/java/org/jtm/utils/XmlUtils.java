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

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        try {
//            System.out.println(getMapFromBpmnData(readContent("E://1.bpmn")));
            readContent("E://1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            document = DocumentHelper.parseText(xmlData.trim());
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
                for (Element element : elements) {
                    map.put(element.getName(), element.getStringValue());
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 根据xmlData 解析BPMN数据
     *
     * @param xmlData xmlData
     * @return 解析后的数据
     */
    static String getMapFromBpmnData(String xmlData) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlData.trim());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (document != null) {
            // 得到根节点下的节点列表
            List<Element> list = document.getRootElement().elements();
            // 遍历二级节点
            for (Element ele : list) {
                if (ele.getQName().getQualifiedName().equals("process"))
                    return ele.attribute("id").getValue();
            }
        }
        return "";
    }

    private static String readContent(String file) throws IOException {
        File f = new File(file);
        StringBuilder stringBuilder = new StringBuilder();
        if (f.exists() && f.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(f);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            byte[] bytes = new byte[1024];
            for (int x = 0; x > -1; ) {
                x = fileInputStream.read(bytes);
                stringBuilder.append(new String(bytes, "GBK"));
                bytes = new byte[1024];
            }
            fileInputStream.close();
        }
        return stringBuilder.toString();
    }
}
