package jsobyra.foodApp.model.database.databaseItems;

public class DatabaseItemReview {
    private int id;
    private int stars;
    private String review;
    private int itemId;

    public DatabaseItemReview(int id, int stars, String review, int itemId) {
        this.id = id;
        this.stars = stars;
        this.review = review;
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
    }
}
