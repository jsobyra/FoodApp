package jsobyra.foodApp.model.database.databaseItems;


public class DatabaseRestaurant {
    private int restaurantId;
    private String restaurantName;
    private int locationId;

    public DatabaseRestaurant(int restaurantId, String restaurantName, int locationId) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.locationId = locationId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getLocationId() {
        return locationId;
    }
}