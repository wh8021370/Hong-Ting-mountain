package com.dingtalk.chatbot;

import com.dingtalk.chatbot.message.MarkdownMessage;
import com.dingtalk.chatbot.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.jtm.entity.Origin;
import org.jtm.entity.PoemEntity;

import java.util.Arrays;

public class Poem2Md {
    public static Message poem2Md(PoemEntity poemEntity) {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle(poemEntity.getData().getContent());

        Origin origin = poemEntity.getData().getOrigin();
        message.add(MarkdownMessage.getHeaderText(1, origin.getTitle()));
        message.add(MarkdownMessage.getHeaderText(3, origin.getAuthor() + " " + origin.getDynasty()));
        message.add(MarkdownMessage.getHeaderText(5, Arrays.toString(origin.getContent())));
        if (StringUtils.isNotEmpty(origin.getTranslate())) {
            message.add(MarkdownMessage.getBoldText(origin.getTranslate()));
        } else {
            message.add(MarkdownMessage.getBoldText("暂无翻译~"));
        }
        return message;
    }
}
