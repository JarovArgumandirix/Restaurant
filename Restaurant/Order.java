package Restaurant;

public interface Order {
    public boolean add(Item one);
    public boolean remove(String name);
    public int removeAll(String name);
    public int itemQuantity();
    public int itemQuantity(String dishName);
    public Item[] getItems();
    public double costTotal();
    public String[] itemsNames();
    public Item[] sortedDishesByCost();
}
