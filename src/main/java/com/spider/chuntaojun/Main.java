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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MatchUrl matchUrl = new MatchUrlImpl();
        for (int i = 100000000; i < 201790139; i ++) {
            FoodBook foodBook = matchUrl.getFoodBooks(matchUrl.getContext("https://www.xiachufang.com/recipe/" + i + "/"));
            if (foodBook != null && foodBook.judge()) {
                Document document = new Document("foodType", foodBook.getFoodType());
                document.append("foodName", foodBook.getFoodName());
                document.append("foodIngredient", foodBook.getFoodIngredient());
                document.append("foodSteps", foodBook.getSteps());
                document.append("foodPicUrls", foodBook.getFoodPicUrls());
                MongoJDBC.InsertDocument(document);
            }
        }
    }

}
