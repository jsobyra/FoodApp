package jsobyra.foodApp.model.database.databaseItems;

public class DatabaseRestaurantReview {
    private int id;
    private int stars;
    private String review;
    private int restaurantId;

    public DatabaseRestaurantReview(int id, int stars, String review, int restaurantId) {
        this.id = id;
        this.stars = stars;
        this.review = review;
        this.restaurantId = restaurantId;
    }

    public int getStars() {
        return stars;
    }

    public int getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
}
