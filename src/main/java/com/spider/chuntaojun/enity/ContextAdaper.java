package com.spider.chuntaojun.enity;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author chuntaojun
 */
public class ContextAdaper {

    public static HashMap<String, Object> HtmlPares(String context) {
        HashMap<String, Object> foodBookMap = new HashMap<>();
        Pattern pattern = compile("<h2 id=\"steps\">(.+?)</h2>");
        Matcher matcher = pattern.matcher(context);
        if (matcher.find()) {
            foodBookMap.put("foodName", matcher.group(1).replaceAll(" +", "").replace("&nbsp;", ""));
        }
        pattern = compile("<a href=\".+?\">(.+?)</a>.+?<td class=\"unit\">(.+?)</td>");
        matcher = pattern.matcher(context);
        HashMap<String, String> foodIngredient = new HashMap<>();
        boolean isFind = matcher.find();
        while (isFind) {
            foodIngredient.put(matcher.group(1), matcher.group(2).trim());
            isFind = matcher.find();
        }
        foodBookMap.put("foodIngredient", foodIngredient);
        pattern = compile("<p class=\"text\" >(.+?)</p>");
        matcher = pattern.matcher(context);
        isFind = matcher.find();
        Set<String> steps = new TreeSet<>();
        while (isFind) {
            steps.add(matcher.group(1));
            isFind = matcher.find();
        }
        foodBookMap.put("foodSteps", steps);
        pattern = compile("<img src=\\\"(.*?)\\\" alt=\\\"(.+?)\\\" width=\\\"(.+?)\\\">");
        matcher = pattern.matcher(context);
        isFind = matcher.find();
        Set<String> foodPicUrls = new TreeSet<>();
        while (isFind) {
            foodPicUrls.add(matcher.group(1));
            isFind = matcher.find();
        }
        foodBookMap.put("foodPicUrls", foodPicUrls);
        return foodBookMap;
    }

}
