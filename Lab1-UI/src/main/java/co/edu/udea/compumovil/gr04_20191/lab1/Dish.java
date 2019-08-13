package co.edu.udea.compumovil.gr04_20191.lab1;

import java.util.ArrayList;

public class Dish {
    private String name;
    private ArrayList<String> time;
    private String type;
    private int prepTime;
    private int price;
    private String ingredients;

    public Dish(String name, ArrayList<String> time, String type, int prepTime, int price, String ingredients) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.prepTime = prepTime;
        this.price = price;
        this.ingredients = ingredients;
    }


    public void setTime(ArrayList<String> time) {
        this.time = time;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }
}
