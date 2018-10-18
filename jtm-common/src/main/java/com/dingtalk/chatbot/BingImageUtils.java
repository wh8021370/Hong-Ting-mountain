
package com.dingtalk.chatbot;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.demo.TestConfig;
import com.dingtalk.chatbot.message.MarkdownMessage;
import com.dingtalk.chatbot.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jtm.constant.CommonUrl;
import org.jtm.entity.BingImage;
import org.jtm.entity.BingResult;

import java.io.IOException;

public class BingImageUtils {
    //    TODO 1.Bing 壁纸抓取，每日一张，定时执行
    public static BingResult getBingImage(String index, String number) throws IOException {
        BingResult bingResult = new BingResult();
        HttpClient httpclient = HttpClients.createDefault();
        StringBuilder stringBuilder = new StringBuilder(CommonUrl.BING_IMAGE_URL);
        if (StringUtils.isNotEmpty(index)) {
            stringBuilder.append("&idx=");
            stringBuilder.append(index);
        }
        if (StringUtils.isNotEmpty(number)) {
            stringBuilder.append("&n=");
            stringBuilder.append(number);
        }
        stringBuilder.append("&nc");
        stringBuilder.append(System.currentTimeMillis());

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        HttpResponse httpResponse = httpclient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            JSONObject obj = JSONObject.parseObject(result);
            bingResult = obj.toJavaObject(BingResult.class);
            System.out.println(bingResult.getImages().get(0).getUrl());
        }
        return bingResult;
    }

    public static Message bingResult2Msg(BingImage image) {
        System.out.println(image.toString());
        MarkdownMessage message = new MarkdownMessage();
        if (StringUtils.isNotEmpty(image.getCopyright())) {
            message.setTitle(image.getCopyright());
        } else {
            message.setTitle("每日BING壁纸");
        }
        message.add(MarkdownMessage.getHeaderText(1, image.getCopyright()));
        message.add(MarkdownMessage.getImageText(CommonUrl.BING_URL + image.getUrl()));
        message.add(MarkdownMessage.getLinkText(image.getCopyright(),CommonUrl.BING_URL + image.getCopyrightlink()));
        return message;
    }

    public static void main(String[] args) {
        DingtalkChatbotClient client = new DingtalkChatbotClient();
        try {
            BingResult bingResult = getBingImage("0", "1");
            client.send(TestConfig.CHATBOT_WEBHOOK, bingResult2Msg(bingResult.getImages().get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
