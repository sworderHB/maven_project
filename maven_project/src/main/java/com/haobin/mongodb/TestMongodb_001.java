package com.haobin.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class TestMongodb_001 {
    @SuppressWarnings("deprecation")
    public static void f1() {
        @SuppressWarnings("deprecation")
        Mongo mongo = new Mongo("localhost",27017);  // Mongo：用于执行一些数据库操作，如建立一个数据库等
       
        // 查看MongoDB数据库列
        System.out.println(mongo.getDatabaseNames());
        
        DB db = mongo.getDB("test");        //DB：对应一个数据库，可以用来建立集合等
        
        // 查看当前数据库下所有集合
        System.out.println(db.getCollectionNames());  
        
        DBCollection collection = db.getCollection("col2");  // DBCollection：对应一个集合（类比关系数据库中的表），可用于增删改查对象
        DBCursor dbCursor = collection.find();
        System.out.println(collection.getCount());  // DBCursor：游标，用来遍历查询取得的数据，实现了Iterable和Iterator
//        while(dbCursor.hasNext()){
//            System.out.println(dbCursor.next());
//        }
       
        
        while(dbCursor.hasNext()){  
//          DBObject：接口，代表一个具体的记录。
            DBObject dbObject = dbCursor.next();  
            //打印该对象的特定字段信息    
            System.out.println("name:"+ dbObject.get("name")+",age:"+dbObject.get("age"));  
            //打印该对象的所有信息  
            System.out.println(dbObject+ "\n\n");  
        }  
        mongo.close();
    }
    
    public static void main(String[] args) {
         f1() ;
    }
}
