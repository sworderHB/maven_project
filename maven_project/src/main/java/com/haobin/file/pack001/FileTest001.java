package com.haobin.file.pack001;

import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FileTest001 {
	private static TreeMap<Double, String> treeMap = new TreeMap<Double, String>();
	
	public static void getDirSize(File file) {     
        //判断文件是否存在     
        if (file.exists()) {     
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {     
                File[] children = file.listFiles();     
                double size = 0;     
                for (File f : children)     
                    getDirSize(f);     
//                return size;     
            } else {//如果是文件则直接返回其大小,以“兆”为单位   
                double size = (double) file.length() / 1024 / 1024; 
                treeMap.put(size, file.getAbsolutePath());
//                return size;     
            }     
        } else {     
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");     
//            return 0.0;     
        }     
    } 
	
	public static void main(String[] args) {
		getDirSize(new File("H:\\youDaoNet"));
		
		for (Entry<Double, String> entry : treeMap.entrySet()) {
			System.out.println(entry.getKey() + ",   "  + entry.getValue());
		}
		
	}
}
