package com.haobin.others;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class Test {
    // String 可以为任意类型 也可以自定义类型
    static Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();

    static {
        keyChanceMap.put("success", 1);
        keyChanceMap.put("error", 10);
//        keyChanceMap.put("出现比例为500的", 500);
//        keyChanceMap.put("出现比例为1000的", 1000);
//        keyChanceMap.put("出现比例为10000的", 10000);
    }

    public  static void f() {
        Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();
        keyChanceMap.put("success", 1);  //success和failure出现的比例是1:10
        keyChanceMap.put("falure", 10);
        
        Map<String, Integer> count = new HashMap<String, Integer>();
        for (int i = 0; i < 100000; i++) {
            String item = chanceSelect(keyChanceMap);
            
            if (count.containsKey(item)) {
                count.put(item, count.get(item) + 1);
            } else {
                count.put(item, 1);
            }
        }

        for (String id : count.keySet()) {
            System.out.println(id + "\t出现了 " + count.get(id) + " 次");
        }
        /**
         * 输出结果类似于：
                 falure   出现了 90843 次
                 success 出现了 9157 次
         */
    }
    
    public static void g() throws Exception {
      //GBK编码格式源码路径   
        String srcDirPath = "C:\\hbCode\\maven_project\\src\\main\\java\\geym";   
        //转为UTF-8编码格式源码路径   
        String utf8DirPath = "C:\\jar\\geym";   
        //获取所有java文件   
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);   
                  
        for (File javaGbkFile : javaGbkFileCol) {   
              //UTF8格式文件路径   
              String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());   
               //使用GBK读取数据，然后用UTF-8写入数据   
              FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));          
        } 
        
    }
    
    public static void main(String[] args) throws Exception {
//        f();
        g();
        
//        Map<String, Integer> count = new HashMap<String, Integer>();
//        for (int i = 0; i < 100000; i++) {
//            String item = chanceSelect(keyChanceMap);
//            
//            if (count.containsKey(item)) {
//                count.put(item, count.get(item) + 1);
//            } else {
//                count.put(item, 1);
//            }
//        }
//
//        for (String id : count.keySet()) {
//            System.out.println(id + "\t出现了 " + count.get(id) + " 次");
//        }
    }


    public static String chanceSelect(Map<String, Integer> keyChanceMap) {
        if (keyChanceMap == null || keyChanceMap.size() == 0)
            return null;

        Integer sum = 0;
        for (Integer value : keyChanceMap.values()) {
            sum += value;
        }
        // 从1开始
        Integer rand = new Random().nextInt(sum) + 1;

        for (Map.Entry<String, Integer> entry : keyChanceMap.entrySet()) {
            rand -= entry.getValue();
            // 选中
            if (rand <= 0) {
                String item = entry.getKey();
                return item;
            }
        }

        return null;
    }
}
