package Restaurant;

public class Address {
    public String name;
    public String city;
    public String avenue;
    public int house;

    Address(String name, String city, String avenue, int house){
        this.name = name;
        this.city = city;
        this.avenue = avenue;
        this.house = house;
    }

    Address(String name, String city, String avenue, String house){
        this.name = name;
        this.city = city;
        this.avenue = avenue;
        this.house = Integer.parseInt(house);
    }
}
