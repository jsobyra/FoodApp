package jsobyra.foodApp.model.items;

import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private Location location;
    private List<Item> items;
    private List<RestaurantReview> review;

    public Restaurant() {
    }

    public Restaurant(int id, String name, Location location, List<Item> items, List<RestaurantReview> review) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.items = items;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public List<RestaurantReview> getReview() {
        return review;
    }

}