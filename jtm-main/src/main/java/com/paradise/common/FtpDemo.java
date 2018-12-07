package com.paradise.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

public class FtpDemo {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(FtpDemo.class);
        String hostname = "112.26.168.118";
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            //连接ftp服务器
            ftpClient.connect(hostname, 20);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.info(">>>> Fail");
                return;
            }
            logger.info("<<< success <<<");
        } catch (MalformedURLException e) {
            logger.error(e.getLocalizedMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
