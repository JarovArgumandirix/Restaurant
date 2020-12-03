package Restaurant;

import java.util.HashMap;

public class OrderManager {
    private HashMap<Address, Order> internetOrders;
    private HashMap<Integer, Order> tableOrders;
    private int[] freeTables;
    private int maxNumberTable;

    OrderManager(){
        internetOrders = new HashMap<>();
        tableOrders = new HashMap<>();
        freeTables = new int[]{1, 2, 3, 4, 5, 6};
        this.maxNumberTable = 6;
    }

    public void add(Order order, int tableNumber) throws Exception {
        if(tableNumber>0 && (tableNumber <= maxNumberTable)) {
            if (!tableOrders.containsKey(tableNumber)) {
                tableOrders.put(tableNumber, order);
                int[] temp = new int[freeTables.length - 1];
                if (tableNumber == freeTables[0]) {
                    for (int i = 1; i < freeTables.length; i++) {
                        temp[i - 1] = freeTables[i];
                    }
                } else {
                    for (int i = 0; i < tableNumber - 1; i++) {
                        temp[i] = freeTables[i];
                    }
                    for (int i = tableNumber; i < freeTables.length; i++) {
                        temp[i - 1] = freeTables[i];
                    }
                }
                freeTables = temp;
            } else {
                throw new OrderAlreadyAddedException("Этот столик " + tableNumber + " уже занят");
            }
        } else{
            throw new IllegalTableNumber();
        }
    }

    public void add(Order order, Address adr) throws OrderAlreadyAddedException {
        if(!internetOrders.containsKey(adr)) {
            internetOrders.put(adr, order);
        } else{
            throw new OrderAlreadyAddedException("По этому адресу " + adr + " уже занят");
        }
    }

    public Order getOrder(int tableNumber) throws IllegalTableNumber {
        if(tableNumber>0 && tableNumber <= maxNumberTable) {
            return tableOrders.get(tableNumber);
        } else{
            throw new IllegalTableNumber();
        }
    }

    public Order getOrder(Address adr){
        return internetOrders.get(adr);
    }

    public void addItem(Item item, int tableNumber) throws IllegalTableNumber {
        if(tableNumber>0 && tableNumber <= maxNumberTable) {
            tableOrders.get(tableNumber).add(item);
        } else{
            throw new IllegalTableNumber();
        }
    }

    public void addItem(Item item, Address adr){
        internetOrders.get(adr).add(item);
    }

    public void removeOrder(int tableNumber) throws IllegalTableNumber {
        if(tableNumber>0 && tableNumber <= maxNumberTable) {
            tableOrders.remove(tableNumber);
            int[] temp = new int[freeTables.length + 1];
            for (int i = 0; i < freeTables.length; i++) {
                temp[i] = freeTables[i];
            }
            temp[freeTables.length] = tableNumber;
            freeTables = temp;
        } else{
            throw new IllegalTableNumber();
        }
    }

    public void removeOrder(Address adr){
        tableOrders.remove(adr);
    }

    public int freeTableNumber() {
        return freeTables[0];
    }

    public int[] freeTableNumbers(){
        return freeTables;
    }

    public Order[] getTableOrders(){
        return tableOrders.values().toArray(new Order[0]);
    }

    public Order[] getInternetOrders(){
        return internetOrders.values().toArray(new Order[0]);
    }

    public double tableOrdersCostSum(){
        Order[] orders = getTableOrders();
        double sum = 0;
        for(int i = 0; i < orders.length; i++){
            sum += orders[i].costTotal();
        }
        return sum;
    }

    public double internetOrdersCostSum(){
        Order[] orders = getInternetOrders();
        double sum = 0;
        for(int i = 0; i < orders.length; i++){
            sum += orders[i].costTotal();
        }
        return sum;
    }

    public int dishTableQuantity(String dishName){
        Order[] orders = getTableOrders();
        int sum = 0;
        for(int i = 0; i < orders.length; i++){
            sum += orders[i].itemQuantity(dishName);
        }
        return sum;
    }

    public int dishInternetQuantity(String dishName){
        Order[] orders = getInternetOrders();
        int sum = 0;
        for(int i = 0; i < orders.length; i++){
            sum += orders[i].itemQuantity(dishName);
        }
        return sum;
    }
}
