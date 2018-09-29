/**
 * <b>工程名：</b>demo<br/>
 * <b>包  名：</b>org.jtm.utils<br/>
 * <b>文件名：</b>ZipUtils.java<br/>
 * <b>日  期：</b>2018/9/28<br/>
 * <b>Copyright (c)</b> 2018 梯升-版权所有<br/>
 */

package org.jtm.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class ZipUtils {
    public static void main(String[] args) throws Exception {
        try {
            readZipFile("E:\\QQ.zip");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void readZipFile(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
            } else {
                System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
                long size = ze.getSize();
                if (size > 0) {
//                    BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        System.out.println(line);
//                    }
//                    br.close();
                }
                System.out.println();
            }
        }
        zin.closeEntry();
    }
}