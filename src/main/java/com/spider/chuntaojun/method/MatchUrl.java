package com.spider.chuntaojun.method;

import com.spider.chuntaojun.enity.FoodBook;

import java.util.ArrayList;

public interface MatchUrl {

    /**
     *
     * @param url
     * @return
     */
    String getContext(String url);

    /**
     *
     * @param context
     * @return
     */
    FoodBook getFoodBooks(String context);

}
