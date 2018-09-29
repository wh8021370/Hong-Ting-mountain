package com.paradise.task;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wh
 * @date 2018/9/18
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        System.out.println("hello world");
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost("https://oapi.dingtalk.com/robot/send?access_token=b2190a6d462ac364424bd9c5c61738c039105d9a1737d97acda1caea290ea134");
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n" +
                "    \"actionCard\": {\n" +
                "        \"title\": \"乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身\", \n" +
                "        \"text\": \"![screenshot](@lADOpwk3K80C0M0FoA) \\n\\n #### 乔布斯 20 年前想打造的苹果咖啡厅 \\n\\n Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划\", \n" +
                "        \"hideAvatar\": \"0\", \n" +
                "        \"btnOrientation\": \"0\", \n" +
                "        \"btns\": [\n" +
                "            {\n" +
                "                \"title\": \"内容不错\", \n" +
                "                \"actionURL\": \"https://www.dingtalk.com/\"\n" +
                "            }, \n" +
                "            {\n" +
                "                \"title\": \"不感兴趣\", \n" +
                "                \"actionURL\": \"https://www.dingtalk.com/\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }, \n" +
                "    \"msgtype\": \"actionCard\"\n" +
                "}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            org.apache.http.HttpResponse response = httpclient.execute(httppost);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
