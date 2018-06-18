package jsobyra.foodApp.model.database;

import jsobyra.foodApp.model.database.databaseItems.*;
import jsobyra.foodApp.model.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class Database {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private int id = 100;

    public ResponseEntity<Order> addOrder(int orderId, Order order) {
        order.getItemsId().forEach(itemId -> {
            jdbcTemplate.update("INSERT INTO Order_History VALUES (?,?,?,?)",
                    id++, orderId, order.getRestaurantId(), itemId);
        });
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<List<FineOrder>> getOrders(int orderId) {
        List<DatabaseOrder> databaseOrders = getAllOrders();
        List<Restaurant> restaurants = getAllRestaurants().getBody();
        List<Item> items = getAllItems().getBody();

        List<FineOrder> orders = databaseOrders.stream()
                .filter(o -> o.getOrderId() == orderId)
                .map(o -> new FineOrder(
                        restaurants.stream().filter(r -> r.getId() == o.getRestaurantId()).findFirst().get().getName(),
                        items.stream().filter(i -> i.getId() == o.getItemId()).collect(Collectors.toList())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private List<DatabaseOrder> getAllOrders() {
        String sql = "SELECT o.id, o.order_id, o.restaurant_id, o.item_id FROM Order_History AS o;";

        List<DatabaseOrder> databaseOrders = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseOrder(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));

        return databaseOrders;
    }

    public ResponseEntity<ItemReview> addItemReviewToItem(int itemId, ItemReview itemReview) {
        jdbcTemplate.update("INSERT INTO Item_Review VALUES (?,?,?,?)",
                itemReview.getId(), itemReview.getStars(), itemReview.getReview(), itemId);
        return new ResponseEntity<>(itemReview, HttpStatus.OK);
    }

    public ResponseEntity<RestaurantReview> addRestaurantReviewToRestaurant(int restaurantId, RestaurantReview restaurantReview) {
        jdbcTemplate.update("INSERT INTO Restaurant_Review VALUES (?,?,?,?)",
                restaurantReview.getId(), restaurantReview.getStars(), restaurantReview.getReview(), restaurantId);
        return new ResponseEntity<>(restaurantReview, HttpStatus.OK);
    }

    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<DatabaseLocation> databaseLocations = getAllDatabaseLocations();
        List<DatabaseRestaurantReview> databaseRestaurantReviews = getAllDatabaseRestaurantReviews();
        List<DatabaseRestaurant> databaseRestaurants = getAllDatabaseRestaurants();

        List<Restaurant> restaurants = databaseRestaurants.stream()
                .map(dr -> {
                        Location location = databaseLocations.stream()
                                .filter(l -> l.getId() == dr.getLocationId())
                                .map(l -> new Location(l.getId(), l.getCountry(), l.getCity(), l.getStreet()))
                                .collect(Collectors.toList()).get(0);
                        List<RestaurantReview> restaurantReviews = databaseRestaurantReviews.stream()
                                .filter(r -> r.getRestaurantId() == dr.getRestaurantId())
                                .map(r -> new RestaurantReview(r.getId(), r.getStars(), r.getReview()))
                                .collect(Collectors.toList());
                        List<Item> items = getAllItemsForRestaurant(dr.getRestaurantId()).getBody();
                        return new Restaurant(dr.getRestaurantId(), dr.getRestaurantName(), location, items, restaurantReviews);
                    }
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    public ResponseEntity<List<Restaurant>> getRestaurantsByCity(String city) {
        return new ResponseEntity<>(getAllRestaurants().getBody().stream()
                .filter(r -> r.getLocation().getCity().equals(city))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<List<Item>> getAllItems() {
        List<DatabaseItemReview> databaseItemReviews = getAllDatabaseItemReviews();
        List<DatabaseItem> databaseItems = getAllDatabaseItems();

        List<Item> items = databaseItems.stream()
                .map(di -> {
                            List<ItemReview> itemReviews = databaseItemReviews.stream()
                                    .filter(r -> r.getItemId() == di.getId())
                                    .map(dir -> new ItemReview(dir.getId(), dir.getStars(), dir.getReview()))
                                    .collect(Collectors.toList());

                            return new Item(di.getId(), di.getName(), di.getPrice(), itemReviews);
                        }
                ).collect(Collectors.toList());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    public ResponseEntity<List<Item>> getItemsInRestaurant(int restaurantId) {
        return new ResponseEntity<>(getAllRestaurants().getBody().stream()
                .filter(r -> r.getId() == restaurantId)
                .collect(Collectors.toList()).get(0).getItems(), HttpStatus.OK);
    }

    public ResponseEntity<List<RestaurantReview>> getAllRestaurantReviews() {
        return new ResponseEntity<>(getAllDatabaseRestaurantReviews().stream()
                .map(r -> new RestaurantReview(r.getId(), r.getStars(), r.getReview()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<List<RestaurantReview>> getRestaurantReviewsByRestaurantId(int restaurantId) {
        return new ResponseEntity<>(getAllRestaurants().getBody().stream()
                .filter(r -> r.getId() == restaurantId)
                .collect(Collectors.toList()).get(0)
                .getReview(), HttpStatus.OK);
    }

    public ResponseEntity<List<ItemReview>> getAllItemReviews() {
        return new ResponseEntity<>(getAllDatabaseItemReviews().stream()
                .map(r -> new ItemReview(r.getId(), r.getStars(), r.getReview()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<List<ItemReview>> getItemReviewsByItemId(int itemId) {
        return new ResponseEntity<>(getAllItems().getBody().stream()
                .filter(i -> i.getId() == itemId)
                .collect(Collectors.toList()).get(0)
                .getReview(), HttpStatus.OK);
    }

    public ResponseEntity<List<Item>> getAllItemsForRestaurant(int restaurantId) {
        List<DatabaseItemReview> databaseItemReviews = getAllDatabaseItemReviews();
        List<DatabaseItem> databaseItems = getAllDatabaseItems();

        List<Item> items = databaseItems.stream()
                .filter(di -> di.getRestaurantId() == restaurantId)
                .map(di -> {
                            List<ItemReview> itemReviews = databaseItemReviews.stream()
                                    .filter(r -> r.getItemId() == di.getId())
                                    .map(dir -> new ItemReview(dir.getId(), dir.getStars(), dir.getReview()))
                                    .collect(Collectors.toList());

                            return new Item(di.getId(), di.getName(), di.getPrice(), itemReviews);
                        }
                ).collect(Collectors.toList());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    private List<DatabaseLocation> getAllDatabaseLocations() {
        String sql = "SELECT l.id, l.country, l.city, l.street FROM Location AS l;";

        List<DatabaseLocation> databaseLocations = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseLocation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));

        return databaseLocations;
    }

    private List<DatabaseRestaurantReview> getAllDatabaseRestaurantReviews() {
        String sql = "SELECT rr.id, rr.stars, rr.review, rr.restaurant_id FROM Restaurant_Review AS rr;";

        List<DatabaseRestaurantReview> databaseRestaurantReviews = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseRestaurantReview(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)));

        return databaseRestaurantReviews;
    }

    private List<DatabaseItemReview> getAllDatabaseItemReviews() {
        String sql = "SELECT ir.id, ir.stars, ir.review, ir.item_id FROM Item_Review AS ir;";

        List<DatabaseItemReview> databaseItemReviews = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseItemReview(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)));

        return databaseItemReviews;
    }

    private List<DatabaseItem> getAllDatabaseItems() {
        String sql = "SELECT i.id, i.name, i.price, i.restaurant_id FROM Item AS i;";

        List<DatabaseItem> databaseItems = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseItem(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)));

        return databaseItems;
    }

    private List<DatabaseRestaurant> getAllDatabaseRestaurants() {
        String sql = "SELECT r.id, r.name, r.location_id FROM Restaurant AS r;";

        List<DatabaseRestaurant> databaseRestaurants = jdbcTemplate.query(sql,
                (rs, rowNum) -> new DatabaseRestaurant(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));

        return databaseRestaurants;
    }
}



