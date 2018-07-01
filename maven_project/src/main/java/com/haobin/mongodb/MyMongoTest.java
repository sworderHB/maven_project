package com.haobin.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.util.JSON;



public class MyMongoTest {

    public static void insert(DB db, String collectionName) {
        DBCollection users = db.getCollection(collectionName);

        /****************************** 第一种方式(put或append) 开始 ***************************/
        // // BasicDBObject 代表了一行记录
        BasicDBObject d1 = new BasicDBObject();
        d1.put("userId", "myU1");
        // 可以直接指定_id
        // d1.put("_id","aaaone111one");
        d1.append("部门", "深圳").append("喜好", "历史222").append("name", "张三").append("age", 33);
        // 后续的同名key 会覆盖掉前面的同名key
        d1.append("age", 99);
        users.insert(d1);// 或者users.save(data1);
        /****************************** 第一种方式 结束 ***************************/



        /****************************** 第2种方式（BasicDBObjectBuilder） 开始 ***************************/
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().add("userId", "u3").add("age", 123);
        users.insert(builder.get());
        /****************************** 第2种方式 结束 ***************************/


        /****************************** 第3种方式（Map） 开始 ***************************/
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("userId", "aaaaa");
        users.insert(new BasicDBObject(data1));
        /****************************** 第3种方式 开始 ***************************/

        
        // 测试
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("userId", "aaaaa");
       
        Map<String, Object> data3 = new HashMap<String, Object>();
        data3.put("userId", "aaaaa");
       
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        listMap.add(data2);
        listMap.add(data3);
        users.insert( );
        
        

        /****************************** 第4种方式(json 字符串) 开始 ***************************/
        // // key 不加单引号 也不会报错
        String json = "{'userId':'t234','age':234, address:'科技园'}";
        // 注意，这里要使用mongodb 提供的JSON
        DBObject d2 = (DBObject) JSON.parse(json);
        users.insert(d2);
        /****************************** 第4种方式 结束 ***************************/
    }

    public static void delete(DB db, String collectionName) {
        DBCollection users = db.getCollection(collectionName);

        // 此语句会删除当前集合中全部的数据
        // users.remove(new BasicDBObject());

        // 此语句无法删除 自动生成的id
        // users.remove(new BasicDBObject("_id", "596c3668276f902b042d62b0"));

        // 此语句可以正确的删除自动生成的id
        // users.remove(new BasicDBObject("_id", new ObjectId("596c3668276f902b042d62b3")) );

        // 会删除所有包含<喜好, 历史>的记录
        users.remove(new BasicDBObject("喜好", "历史"));
    }


    public static void update(DB db, String collectionName) {
        DBCollection users = db.getCollection(collectionName);
        BasicDBObject condition = new BasicDBObject();
        condition.append("userId", "aaaaa");


        // OK 更新全部满足条件的记录
        users.update(new BasicDBObject("userId", "aaaaa"),
                new BasicDBObject("$set", new BasicDBObject("userId", "myU2").append("name", "lisi")), false, true);

        // 只能修改第一个满足条件的记录
        // users.update(condition, new BasicDBObject("userId", "myU2").append("name", "lisi"));



        // error 此语句无法达到同时更新多条记录的效果 运行报错：Exception in thread "main" java.lang.IllegalArgumentException: Replacements can not be multi
        // users.updateMulti(condition, new BasicDBObject("userId", "myU2").append("name", "lisi"));

        // OK 经测试发现，正确的写法是： 第二个参数要写成new BasicDBObject("$set"。。。。。。。。。。。。。。。。。。)
        users.updateMulti(condition,
                new BasicDBObject("$set", new BasicDBObject("userId", "myU2").append("name", "lisi")));   // 97



//        users.update(condition, new BasicDBObject("userId", "myU2").append("name", "lisi"), false, true);

        // error 行报错：Exception in thread "main" java.lang.IllegalArgumentException: Replacements can not be multi
        // users.update(new BasicDBObject("userId", "aaaaa"), new BasicDBObject("userId", "myU2").append("name", "lisi"), false, true);



        // users.update(new BasicDBObject("userId", "myU2"), new BasicDBObject("$set", new
        // BasicDBObject("name", "newName")));
    }

    public static void main(String[] args) throws Exception {
        MongoClientOptions mcs = MongoClientOptions.builder().connectionsPerHost(6).connectTimeout(1000).build();
        MongoClient mongo = new MongoClient("localhost:27017", mcs);
        System.out.println("ConnectionsPerHost==" + mongo.getMongoClientOptions().getConnectionsPerHost());
        DB db = mongo.getDB("mydb");

//        insert(db, "users");
        // delete(db, "users");
        update(db, "users");


        // DBObject d = users.findOne();
        // System.out.println("d=="+d);

        // {userId:"ttuu" , age:{$gte:100,$lt:200} }

        // DBObject con = new BasicDBObject(
        // "userId","ttuu").append(
        // "age", new BasicDBObject("$gt",120).append("$lt", 210)
        // );
        //
        // DBObject show = new BasicDBObject("userId",1).append("age",1).append("_id", 0);
        //
        // DBCursor c = users.find(con,show).limit(2);
        // while(c.hasNext()){
        // DBObject d = c.next();
        // System.out.println("d==="+d);
        // }


        mongo.close();

    }
}
