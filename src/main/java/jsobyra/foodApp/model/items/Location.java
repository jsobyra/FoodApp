package jsobyra.foodApp.model.items;

public class Location {
    private final int id;
    private final String country;
    private final String city;
    private final String street;

    public Location(int id, String country, String city, String street) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }
}
