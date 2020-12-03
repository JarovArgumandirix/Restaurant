package Restaurant;

public class RestaurantOrder implements Order {
    private int size;
    private Item[] items;

    RestaurantOrder(){
        size = 0;
    }

    RestaurantOrder(Item one){
        items = new Item[1];
        size = 1;
        items[0] = one;
    }

    RestaurantOrder(Item[] orders){
        items = orders;
        size = orders.length;
    }

    public boolean add(Item one){
        if(size != 0) {
            Item[] temp = new Item[size + 1];
            for (int i = 0; i < size; i++) {
                temp[i] = items[i];
            }
            items = temp;
            items[size] = one;
            size++;
        } else{
            items = new Item[1];
            size = 1;
            items[0] = one;
        }
        return true;
    }

    private void removing(int j){
        Item[] temp = new Item[size - 1];
        for(int i = 0; i<j; i++){
            temp[i] = items[i];
        }
        for(int i = j+1; i<size; i++){
            temp[i-1] = items[i];
        }
        items = temp;
        size--;
    }

    public boolean remove(String name){
        boolean flag = false;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(name)){
                removing(i);
                flag = true;
                i = -1;
            }
        }
        return flag;
    }

    public int removeAll(String name){
        int flag = 0;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(name)){
                removing(i);
                flag++;
            }
        }
        return flag;
    }

    public int itemQuantity(){
        return size;
    }

    public int itemQuantity(String dishName){
        int flag = 0;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(dishName)){
                flag++;
            }
        }
        return flag;
    }

    public Item[] getItems(){
        return items;
    }

    public double costTotal(){
        double sum = 0;
        for(int i = 0; i<size; i++){
            sum += items[i].getCost();
        }
        return sum;
    }

    public String[] itemsNames(){
        String [] itemsNames = new String[size];
        int newSize = 1;
        itemsNames[0] = items[0].getName();
        for(int i = 1; i < size; i++){
            String newName = items[i].getName();
            boolean flag = true;
            for(int j = 0; j < newSize; j++){
                if (itemsNames[j].equals(newName)) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                itemsNames[newSize] = newName;
                newSize++;
            }
        }
        String[] names = new String[newSize];
        for(int i = 0; i < newSize; i++){
            names[i] = itemsNames[i];
        }
        return names;
    }

    public Item[] sortedDishesByCost(){
        Item[] temp = items;
        for(int i = 0; i < size-1; i++){
            for(int j = i; j<size; j++){
                if(temp[i].getCost() < temp[j].getCost()){
                    Item temp_one = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp_one;
                }
            }
        }
        return temp;
    }

}
