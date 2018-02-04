package com.spider.chuntaojun.enity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chuntaojun
 */
public class FoodBook {

    private String foodType;
    private String foodName;
    private Map<String, String> foodIngredient;
    private Set<String> foodPicUrls;
    private Set<String> steps;

    public FoodBook(HashMap<String, Object> context) {
        this.foodName = (String) context.get("foodName");
        this.foodIngredient = (Map<String, String>) context.get("foodIngredient");
        this.foodPicUrls = (Set<String>) context.get("foodPicUrls");
        this.steps = (Set<String>) context.get("foodSteps");
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Map<String, String> getFoodIngredient() {
        return foodIngredient;
    }

    public void setFoodIngredient(Map<String, String> foodIngredient) {
        this.foodIngredient = foodIngredient;
    }

    public Set<String> getFoodPicUrls() {
        return foodPicUrls;
    }

    public void setFoodPicUrls(Set<String> foodPicUrls) {
        this.foodPicUrls = foodPicUrls;
    }

    public Set<String> getSteps() {
        return steps;
    }

    public void setSteps(Set<String> steps) {
        this.steps = steps;
    }

    public boolean judge() {
        if (this.foodIngredient == null || this.steps == null || this.foodName == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBook{" +
                "foodType='" + foodType + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodIngredient=" + foodIngredient +
                ", foodPicUrls=" + foodPicUrls +
                ", steps=" + steps +
                '}';
    }
}
