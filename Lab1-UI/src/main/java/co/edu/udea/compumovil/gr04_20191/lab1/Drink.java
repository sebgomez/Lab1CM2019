package co.edu.udea.compumovil.gr04_20191.lab1;

public class Drink {
    private String name;
    private int price;
    private String ingredients;

    public Drink(String name, int price, String ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}

