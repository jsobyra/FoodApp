package jsobyra.foodApp.model.database.databaseItems;

public class DatabaseOrder {
    private int id;
    private int orderId;
    private int restaurantId;
    private int itemId;

    public DatabaseOrder(int id, int orderId, int restaurantId, int itemId) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getItemId() {
        return itemId;
    }
}
