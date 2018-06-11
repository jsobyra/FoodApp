package jsobyra.foodApp.model.items;

import java.util.List;

public class Order {
    private int restaurantId;
    private List<Integer> itemsId;

    public Order(){}

    public Order(int restaurantId, List<Integer> itemsId) {
        this.restaurantId = restaurantId;
        this.itemsId = itemsId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Integer> getItemsId() {
        return itemsId;
    }

    public void setItemsId(List<Integer> itemsId) {
        this.itemsId = itemsId;
    }
}
