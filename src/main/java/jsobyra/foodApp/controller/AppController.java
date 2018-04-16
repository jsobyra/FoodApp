package jsobyra.foodApp.controller;


import jsobyra.foodApp.model.database.Database;
import jsobyra.foodApp.model.items.Item;
import jsobyra.foodApp.model.items.ItemReview;
import jsobyra.foodApp.model.items.Restaurant;
import jsobyra.foodApp.model.items.RestaurantReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {
    private Database database;

    @Autowired
    public AppController(Database database) {
        this.database = database;
    }

    @RequestMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return database.getAllRestaurants();
    }

    @RequestMapping("/restaurants/city")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCity(@RequestParam(value = "city") String city) {
        return database.getRestaurantsByCity(city);
    }

    @RequestMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        return database.getAllItems();
    }

    @RequestMapping("/restaurant/items")
    public ResponseEntity<List<Item>> getItemsInRestaurant(@RequestParam(value = "restaurantId") int restaurantId) {
        return database.getItemsInRestaurant(restaurantId);
    }

    @RequestMapping("/restaurantReviews")
    public ResponseEntity<List<RestaurantReview>> getAllRestaurantReviews() {
        return database.getAllRestaurantReviews();
    }

    @RequestMapping("/restaurant/restaurantReviews")
    public ResponseEntity<List<RestaurantReview>> getRestaurantReviewsByRestaurantId(@RequestParam(value = "restaurantId") int restaurantId) {
        return database.getRestaurantReviewsByRestaurantId(restaurantId);
    }

    @RequestMapping("/itemReviews")
    public ResponseEntity<List<ItemReview>> getAllItemReviews() {
        return database.getAllItemReviews();
    }

    @RequestMapping("/restaurant/itemReviews")
    public ResponseEntity<List<ItemReview>> getItemReviewsByItemId(@RequestParam(value = "itemId") int itemId) {
        return database.getItemReviewsByItemId(itemId);
    }

    @RequestMapping(value = "/itemReview", method = RequestMethod.POST)
    public ResponseEntity<ItemReview> addItemReviewToItem(@RequestParam(value = "itemId") int itemId,
                                                                @RequestBody ItemReview itemReview) {
        return database.addItemReviewToItem(itemId, itemReview);
    }

    @RequestMapping(value = "/restaurantReview", method = RequestMethod.POST)
    public ResponseEntity<RestaurantReview> addRestaurantReviewToRestaurant(@RequestParam("restaurantId") int restaurantId,
                                                                            @RequestBody RestaurantReview restaurantReview) {
        return database.addRestaurantReviewToRestaurant(restaurantId, restaurantReview);
    }
}
