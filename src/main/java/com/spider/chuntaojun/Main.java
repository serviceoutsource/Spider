package com.spider.chuntaojun;

import com.spider.chuntaojun.dao.MongoJDBC;
import com.spider.chuntaojun.enity.FoodBook;
import com.spider.chuntaojun.method.MatchUrl;
import com.spider.chuntaojun.method.impl.MatchUrlImpl;
import org.bson.Document;

/**
 * @author chuntaojun
 */
public class Main {

    public static MatchUrl matchUrl = new MatchUrlImpl();


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 100002998; i < 201790139; i ++) {
            if ((i&2) == 0) {
                try {
                    Insert(matchUrl.getContext("https://www.xiachufang.com/recipe/" + i + "/"), i);
                    System.out.println("sleep: " + i);
                    System.out.println("Thread sleep 10 s");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Insert(matchUrl.getContext("https://www.xiachufang.com/recipe/" + i + "/"), i);
            }
        }
    }

    public static void Insert(String context, long foodCode) {
        FoodBook foodBook = matchUrl.getFoodBooks(context);
        if (foodBook != null && foodBook.judge()) {
            Document document = new Document("foodCode", foodCode);
            document.append("foodType", foodBook.getFoodType());
            document.append("foodName", foodBook.getFoodName());
            document.append("foodIngredient", foodBook.getFoodIngredient());
            document.append("foodSteps", foodBook.getSteps());
            document.append("foodPicUrls", foodBook.getFoodPicUrls());
            MongoJDBC.InsertDocument(document);
        }
    }

}
