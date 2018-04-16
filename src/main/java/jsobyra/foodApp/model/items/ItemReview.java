package jsobyra.foodApp.model.items;

public class ItemReview {
    private final int id;
    private int stars;
    private String review;

    public ItemReview(int id, int stars, String review) {
        this.id = id;
        this.stars = stars;
        this.review = review;
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
}
