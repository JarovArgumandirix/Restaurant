package Restaurant;

public final class Dish implements Item {
    final double cost;
    final String name;
    final String description;

    Dish(String name, String description) throws IllegalArgumentException {
        if(name != null && name !="" && description != null && description != "") {
            this.name = name;
            this.description = description;
            this.cost = 0;
        } else{
            throw new IllegalArgumentException("Попытка создать пустой объект или отрицательную цену");
        }
    }

    Dish(String name, String description, double cost) throws IllegalArgumentException {
        if(name != null && name !="" && description != null && description != "") {
            this.name = name;
            this.description = description;
            this.cost = cost;
        } else{
            throw new IllegalArgumentException("Попытка создать пустой объект или отрицательную цену");
        }
    }

    public double getCost(){ return cost; }
    public String getName(){ return name; }
    public String getDescription(){ return description; }
}
