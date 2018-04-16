package jsobyra.foodApp.model.database.databaseItems;

public class DatabaseItem {
    private int id;
    private String name;
    private double price;
    private int restaurantId;

    public DatabaseItem(int id, String name, double price, int restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
}
