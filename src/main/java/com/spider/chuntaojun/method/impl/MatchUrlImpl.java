package com.spider.chuntaojun.method.impl;

import com.spider.chuntaojun.enity.ContextAdaper;
import com.spider.chuntaojun.enity.FoodBook;
import com.spider.chuntaojun.method.MatchUrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


/**
 * @author chuntaojun
 */
public class MatchUrlImpl implements MatchUrl {

    public static String HEADERS_Mozilla = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:58.0) Gecko/20100101 Firefox/58.0";
    public static String HEADERS_Safari = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/604.5.6 (KHTML, like Gecko) Version/11.0.3 Safari/604.5.6";
    public static String HEADERS_Chrome = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36";

    @Override
    public String getContext(String url) {
        StringBuffer context = new StringBuffer();
        BufferedReader br = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.addRequestProperty("User-Agent", HEADERS_Chrome);
            connection.connect();
            if (connection.getResponseCode() != 404) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String tempLine;
                while ((tempLine = br.readLine()) != null) {
                    context.append(tempLine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context.toString();
    }

    @Override
    public FoodBook getFoodBooks(String context) {
        Pattern urlPattern = compile("<table>.+?</table>.+?<h2 id=\"steps\">.+?</h2>.+?<div class=\"steps\">.+?</div>");
        Matcher matcher = urlPattern.matcher(context);
        Pattern pattern = compile("<a href=\"/category/[0-9]+/\" title=\"([\\u4e00-\\u9fa5]+)\">");
        Matcher matcher1 = pattern.matcher(context);
        while (matcher.find()) {
            FoodBook foodBook = new FoodBook(ContextAdaper.HtmlPares(matcher.group()));
            if (matcher1.find()) {
                foodBook.setFoodType(matcher1.group(1));
            }
            return foodBook;
        }
        return null;
    }
}
