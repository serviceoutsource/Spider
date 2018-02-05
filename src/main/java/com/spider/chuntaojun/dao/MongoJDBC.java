package com.spider.chuntaojun.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 * @author chuntaojun
 */
public final class MongoJDBC {

    private static MongoDatabase mongoDatabase;

    static {
        MongoClient mongoClient = new MongoClient("120.24.90.180", 5000);
        mongoDatabase = mongoClient.getDatabase("HyperFlex");
        System.out.println("Connect to database successfully");
    }

    public static MongoCollection<Document> getMongoCollection() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("FoodBook");
        return collection;
    }

    public static void InsertDocument(Document document) {
        MongoCollection<Document> collection = getMongoCollection();
        collection.insertOne(document);
        System.out.println("Document insert success");
    }

}
