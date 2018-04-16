package jsobyra.foodApp.model.database.databaseItems;

public class DatabaseLocation {
    private int id;
    private String country;
    private String city;
    private String street;

    public DatabaseLocation(int id, String country, String city, String street) {
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
