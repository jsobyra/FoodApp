DROP TABLE Restaurant_Review;
DROP TABLE Item_Review;
DROP TABLE Item;
DROP TABLE Restaurant;
DROP TABLE Location;
DROP TABLE OrderHistory;

CREATE TABLE IF NOT EXISTS Location (
    id int NOT NULL,
    country varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Restaurant (
    id int NOT NULL,
    name varchar(100) NOT NULL,
    location_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (location_id) REFERENCES Location(id)
);

CREATE TABLE IF NOT EXISTS Restaurant_Review (
    id int NOT NULL,
    stars int NOT NULL,
    review varchar(255) NOT NULL,
    restaurant_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);

CREATE TABLE IF NOT EXISTS Item (
    id int NOT NULL,
    name varchar(100) NOT NULL,
    price double NOT NULL,
    restaurant_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);

CREATE TABLE IF NOT EXISTS Item_Review (
    id int NOT NULL,
    stars int NOT NULL,
    review varchar(255) NOT NULL,
    item_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES Item(id)
);

CREATE TABLE IF NOT EXISTS Order_History (
    id int NOT NULL,
    order_id int NOT NULL,
    restaurant_id int NOT NULL,
    item_id int NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO Location (id, country, city, street) VALUES
(1, 'Polska', 'Krakow', 'Karola Szymanowskiego 15'),
(2, 'Polska', 'Tarnow', 'Swietego Marka 22');

INSERT INTO Restaurant (id, name, location_id) VALUES
(1, 'Gora Olimp Pizza&Pub', 1),
(2, 'M22', 2);

INSERT INTO Restaurant_Review (id, stars, review, restaurant_id) VALUES
(1, 4, 'Przyjemne miejsce na pogaduszki. Fajna lokalizacja. Polecam!!!', 1),
(2, 3, 'Pizza ok, piwo w tej cenie nie jest najlepsze. Ogolnie ok', 1),
(3, 5, 'Pyszne burgery. Najlepsze w Krakowie. Jadlem dopiero pierwszy raz ale wroce na pewno!!!', 2);

INSERT INTO Item (id, name, price, restaurant_id) VALUES
(1, 'Piwo', 5.50, 1),
(2, 'Piwo z sokiem', 6.50, 1),
(3, 'Pizza', 20.00, 1),
(4, 'Burger z marynowanym kurczakiem', 22.00, 2),
(5, 'Burger z bekonem', 23.00, 2),
(6, 'Burger z bekonem', 19.00, 1);

INSERT INTO Item_Review (id, stars, review, item_id) VALUES
(1, 3, 'Piwo ok. Bez fajerwerkow', 1),
(2, 4, 'Smaczna dobra pizza', 3),
(3, 5, 'Pyszny burger. Polecam', 4),
(4, 5, 'Najlepszy burger jakiego jadlem', 5),
(5, 5, 'Bardzo dobry burger. Polecam', 6),
(6, 4, 'Bardzo dobry burger. Chociaz ostatnim razem bulka byla stara', 6);


    private int id;
    private String name;
    private Location location;
    private List<Item> items;
    private List<RestaurantReview> review;

SELECT r.id, r.name, l.id, l.country, l.city, l.street, i.id, i.name, i.price,
        rr.id, rr.stars, rr.review, ir.id, ir.stars, ir.review
FROM Restaurant AS r
INNER JOIN Location AS l ON r.location_id = l.id
INNER JOIN Item AS i ON i.restaurant_id = r.id
INNER JOIN Restaurant_Review AS rr ON rr.restaurant_id = r.id
INNER JOIN Item_Review AS ir ON ir.item_id = i.id;