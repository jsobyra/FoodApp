package jsobyra.foodApp.model.items;

public class ItemReview {
    private int id;
    private int stars;
    private String review;

    public ItemReview() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
