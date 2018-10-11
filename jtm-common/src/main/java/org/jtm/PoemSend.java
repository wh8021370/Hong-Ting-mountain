
package org.jtm;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.Poem2Md;
import com.dingtalk.chatbot.demo.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jtm.entity.PoemEntity;

import java.io.IOException;

public class PoemSend {
    private static String TOKEN = "Xtw9qKYqtWSYAz51waUbbalXV/w2+w87";
    private static String URL = "https://v2.jinrishici.com/one.json";

    public static PoemEntity getPoem() throws IOException {
        PoemEntity poemEntity = new PoemEntity();
        HttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(URL);
        httpGet.addHeader("Content-Type", "application/json; charset=utf-8");
        httpGet.addHeader("X-User-Token", TOKEN);

        HttpResponse httpResponse = httpclient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            JSONObject obj = JSONObject.parseObject(result);
            poemEntity = obj.toJavaObject(PoemEntity.class);
            System.out.println(obj);
            System.out.println(">>>>>>>>>>>>>>>");
        }
        return poemEntity;
    }

    public static void main(String[] args) {

        DingtalkChatbotClient client = new DingtalkChatbotClient();
        try {
            PoemEntity poemEntity = PoemSend.getPoem();
            client.send(TestConfig.CHATBOT_WEBHOOK, Poem2Md.poem2Md(poemEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
