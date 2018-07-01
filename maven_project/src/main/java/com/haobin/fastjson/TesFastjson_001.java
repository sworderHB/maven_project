package com.haobin.fastjson;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class TesFastjson_001 {
    public static void main(String[] args) {
//        map2json();
//        map2JSON();

//        javaBean2JsonString(); 
//        javaBean2JSONObject();
        json2JSONObject();
//        jsonArrayt2List();
//        json2SortedMap();
    }

    /**
     * <pre class="code">
     * <b>{@code
     *   Title: javaBean和json字符串的相互转化
     *  
     * 
     * &#64;author  haobin
     * &#64;date    2017年7月18日 上午11:18:47
     * </b>
     * </pre>
     */
    public static void javaBean2JsonString() {
        Student student1 = new Student("aa", 111);
    
        // javaBean转json字符串
        String str = JSON.toJSONString(student1, false);
        System.out.println(str);
    
        // json字符串转javaBean
        Student student2 = JSON.parseObject(str, Student.class);
        System.out.println(student2);
        System.out.println(student1 == student2);  // false
    }


    /**
     * <pre class="code">
     * <b>{@code
     *   Title:  javaBean和JSONObject的相互转化
     *   
     *   JSONObject：相当于Map<String, Object>
     * 
     * &#64;author  haobin
     * &#64;date    2017年7月18日 下午12:05:22
     * </b>
     * </pre>
     */
    public static void javaBean2JSONObject() {
        Student student1 = new Student("aa", 111);
    
        // javaBean 转 JSONObject
        JSONObject object = (JSONObject) JSON.toJSON(student1);
        System.out.println(object.getString("name") + ", " + object.getInteger("age"));
    
        // JSONObject 无法直接转化为JavaBean 一般先将JSONObject转化为json字符串，再把json字符串转化为JavaBean
        System.out.println(object.toJSONString());
        Student student2 = JSON.parseObject(object.toJSONString(), Student.class);
        System.out.println(student2);
        System.out.println(student1 == student2);
    }

    /**
     * <pre class="code">
     * <b>{@code
     *   Title: json字符串和JSONObject的相互转化
     * 
     * &#64;author  haobin
     * &#64;date    2017年7月18日 下午2:10:07
     * </b>
     * </pre>
     */
    @Test
    public static void json2JSONObject() {
        String jsonStr = "{data:{redirectUrl:\"aaa\"},code:\"200\",msg:\"操作成功\"}";
        // json字符串转JSONObject
        JSONObject object = JSON.parseObject(jsonStr);
        System.out.println(JSON.parseObject(object.getString("data")).getString("redirectUrl"));
    
        // JSONObject 转 json字符串
        System.out.println(object.toJSONString());
    }

    /**
     * <pre class="code">
     * <b>{@code
     *   Title: 
     *  json字符串数组(依然是个字符串) 和 List<JavaBean>的相互转化
     * 
     * &#64;author  haobin
     * &#64;date    2017年7月18日 下午2:34:26
     * </b>
     * </pre>
     */
    public static void jsonArrayt2List() {
        // json字符串数组(依然是个字符串) 转化 List<JavaBean>
        String json_array = "[{\"name\":\"aa\",\"age\":111},{\"name\":\"aa2\",\"age\":222}]";
        List<Student> studentList = JSON.parseArray(json_array, Student.class);
    
        for (Student student : studentList) {
            System.out.println(student);
        }
    
        // List<JavaBean> 转化为 json字符串数组(依然是个字符串)
        String json_array2 = JSON.toJSONString(studentList, false);
        System.out.println(json_array2);
    }

    public static void json2SortedMap() {
        // 经测试 key可以不加双引号 value必须的加双引号
//        String jsonStr = "{\"age\":42,\"id\":1,\"male\":true,\"name\":\"Sherlock Holmes\",\"schoolId\":1}";
        String jsonStr = "{mchNo:\"张三\",\"totalAmount\":22}";
        
        
        System.out.println("无序遍历结果：");
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("-------------------");
        System.out.println("有序遍历结果：");
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>() {});
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    

    /**
     * 通过fastjson把Map换成字符串转
     */
    @Test
    public  void map2json() {
        // 创建一个Map对象
        Map<String, String> map = new LinkedHashMap<String, String>();  //150
        map.put("username", "周伯通");
        map.put("address", "广东省仙游谷");
        map.put("111", "1");
        map.put("222", "2");
        map.put("555", "3");
        map.put("444", "4");
        map.put("333", "5");
    
        /**
         * 第二个参数true 表示格式化，会加回车空格之类; false表示不格式化
         * 因为这里map是有序的，所以转化成的json字符串自然也是有序的， 
         * 如果把150行的LinkedHashMap改为HashMap，则最终转化成的json字符串自然会变成无序的 
         */
        String json_str = JSON.toJSONString(map, false); // 转成JSON数据
        System.out.println(json_str);
    
    
        System.out.println("----------JSON.parse(json_str) 转化成的Map是无序的----------------------");
        Map<String, String> map1 = (Map<String, String>) JSON.parse(json_str);
        // 遍历数组数据
        for (String key : map1.keySet()) {
            System.out.println(key + ":" + map1.get(key));
        }
        System.out.println("");
        
        System.out.println("---------JSON.parseObject(json_str,new TypeReference<LinkedHashMap<String, String>>() {}) 转化成的Map是有序的-----------------------");
        Map<String, String> linkedHashMap = JSON.parseObject(json_str, 
                new TypeReference<LinkedHashMap<String, String>>() {});
        // 遍历数组数据
        for (String key : linkedHashMap.keySet()) {
            System.out.println(key + ":" + map1.get(key));
        }
    }

   
    
    /**
     * 通过fastjson把Map换成字符串转
     */
    public static void map2JSON() {
        Map map = new HashMap();
        map.put("username", "周伯通");
        map.put("address", "广东省仙游谷");
        map.put("age", "198");
        String json = JSON.toJSONString(map);
        Map map1 = JSON.parseObject(json);
        for (Object obj : map.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) obj;
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }
}
