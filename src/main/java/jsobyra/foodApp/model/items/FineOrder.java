package jsobyra.foodApp.model.items;

import java.util.List;

public class FineOrder {
    private String restaurant;
    private List<Item> items;

    public FineOrder(){}

    public FineOrder(String restaurant, List<Item> items) {
        this.restaurant = restaurant;
        this.items = items;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}