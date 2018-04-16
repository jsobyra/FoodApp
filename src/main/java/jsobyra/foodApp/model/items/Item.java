package jsobyra.foodApp.model.items;

import java.util.List;

public class Item {
    private final int id;
    private final String name;
    private final double price;
    private final List<ItemReview> review;

    public Item(int id, String name, double price, List<ItemReview> review) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.review = review;
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

    public List<ItemReview> getReview() {
        return review;
    }
}
