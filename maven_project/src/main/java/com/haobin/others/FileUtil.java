package com.haobin.others;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class FileUtil {
    public static boolean isUtf8(File file) throws Exception {
        InputStream in = new java.io.FileInputStream(file);
        byte[] b = new byte[3];
        in.read(b);
        in.close();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65)
            return true;
        else
            return false;
    }

    public static void convertFileEncoding(String srcDirPath, String utf8DirPath) throws Exception {
        //获取所有java文件   
        Collection<File> javaGbkFileCol =
                FileUtils.listFiles(new File(srcDirPath), new String[] {"java"}, true);

        for (File javaGbkFile : javaGbkFileCol) {
//            System.out.println(javaGbkFile.getAbsolutePath() + " 原来不是 utf-8 文件!");

            //UTF8格式文件路径   
            String utf8FilePath =
                    utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据   
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8",
                    FileUtils.readLines(javaGbkFile, "GBK"));
        }
    }
    
    public static void main(String[] args) throws Exception{
        convertFileEncoding(
        		"G:\\MyEclipseWk\\spring\\highlight_spring4\\src", 
        		"G:\\MyEclipseWk\\spring\\highlight_spring4\\src2");
    }
}
