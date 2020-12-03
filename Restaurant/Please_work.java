package Restaurant;

public class Please_work {
    public static void main(String[] args) throws Exception {
        Address kanna = new Address("Канна", "Иоданар", "Возрождения", 7);
        Item apple = new Dish("Яблоко", "зеленое", 5);
        Item juice = new Drink("Сок", "из малины", 15);
        Item chicken = new Dish("Курица", "жареная", 30);

        Order orderRanagon = new RestaurantOrder();

        Order orderKanna = new InternetOrder(apple);
        orderKanna.add(chicken);
        orderKanna.add(juice);
        orderKanna.add(apple);


        Item[] names = orderKanna.getItems();
        System.out.println("Канна заказала:");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i].getName());
        }
        System.out.println("На сумму: " + orderKanna.costTotal() + " медных");
        System.out.println("Яблок было заказано: " + orderKanna.itemQuantity("Яблоко"));
        System.out.println("Уникальные виды продуктов:");
        String[] product = orderKanna.itemsNames();
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i]);
        }

        orderKanna.removeAll("Яблоко");
        names = orderKanna.getItems();
        System.out.println("Канна заказала:");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i].getName());
        }

        System.out.println("/////////");

        Order restaurantKanna = new RestaurantOrder(apple);

        restaurantKanna.add(juice);
        restaurantKanna.add(apple);


        names = restaurantKanna.getItems();
        System.out.println("Канна заказала:");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i].getName());
        }
        System.out.println("На сумму: " + restaurantKanna.costTotal() + " медных");
        System.out.println("Яблок было заказано: " + restaurantKanna.itemQuantity("Яблоко"));
        System.out.println("Уникальные виды продуктов:");
        product = restaurantKanna.itemsNames();
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i]);
        }

        System.out.println("/////////");

        OrderManager manager = new OrderManager();
        int[] tables = manager.freeTableNumbers();
        for (int i = 0; i < tables.length; i++) {
            System.out.println(tables[i]);
        }
        System.out.println("Свободен стол номер " + manager.freeTableNumber());
        try {
            manager.add(restaurantKanna, 3);
        } catch (OrderAlreadyAddedException e){
            System.err.println(e);
        }
        catch (IllegalTableNumber ex){ }
        System.out.println("Ваш заказ был добавлен");
        tables = manager.freeTableNumbers();
        for (int i = 0; i < tables.length; i++) {
            System.out.println(tables[i]);
        }
        try {
            manager.add(restaurantKanna, 31);
        } catch (OrderAlreadyAddedException e){
            System.err.println(e);
        }
        catch (IllegalTableNumber ex){ }

        System.out.println("Заказы на сумму " + manager.tableOrdersCostSum());
        try {
            manager.removeOrder(3);
        } catch (IllegalTableNumber ex) { }
        tables = manager.freeTableNumbers();
        for (int i = 0; i < tables.length; i++) {
            System.out.println(tables[i]);
        }

        try {
            manager.add(orderKanna, kanna);
        } catch (OrderAlreadyAddedException e){
            System.err.println(e);
        }
        Order[] orders = manager.getInternetOrders();
        System.out.println("Заказы на сумму " + manager.internetOrdersCostSum());
        System.out.println(orders[0].costTotal());
    }
}
