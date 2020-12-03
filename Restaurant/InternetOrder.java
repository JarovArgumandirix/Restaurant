package Restaurant;

public class InternetOrder implements Order  {
    private int size;
    private ListNode head;
    private ListNode tail;

    InternetOrder(){
        this.size = 0;
    }

    InternetOrder(Item item){
        add(item);
    }

    InternetOrder(Item[] items){
        addAll(items);
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(Item value) {
        if (value != null) {
            if (this.head == null) {
                this.head = new ListNode(value);
                this.head.previous = this.head.next = this.head;
                this.tail = this.head;
            } else {
                ListNode node = new ListNode(this.head, this.tail, value);
                this.tail.next = this.head.previous = node;
                this.head = node;
            }
            this.size++;
        }
    }

    public void addLast(Item value) {
        if (value != null) {
            if (this.head == null) {
                addFirst(value);
            } else {
                ListNode node = new ListNode(this.head, this.tail, value);
                this.head.previous = this.tail.next = node;
                this.tail = node;
                this.size++;
            }
        }
    }

    public boolean add(Item one) {
        addLast(one);
        return true;
    }

    public boolean addAll(Item[] values) {
        boolean result;
        if (result = values != null) {
            for (Item value : values) {
                result = result && add(value);
            }
        }
        return result;
    }

    public Item[] getItems(){
        Item[] items = new Item[size];
        items[0] = this.head.value;
        int i = 1;
        for(ListNode node = this.head.next; node != this.head; node = node.next){
            items[i] = node.value;
            i++;
        }
        return items;
    }

    private Item[] removing(int j, Item[] items){
        Item[] temp = new Item[size - 1];
        for(int i = 0; i<j; i++){
            temp[i] = items[i];
        }
        for(int i = j+1; i<size; i++){
            temp[i-1] = items[i];
        }
        items = temp;
        size--;

        return items;
    }

    public boolean remove(String name){
        Item[] items = getItems();
        boolean flag = false;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(name)){
                items = removing(i, items);
                flag = true;
                i = -1;
            }
        }
        clear();
        addAll(items);
        return flag;
    }

    public int removeAll(String name){
        Item[] items = getItems();
        int flag = 0;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(name)){
                items = removing(i, items);
                flag++;
            }
        }
        clear();
        addAll(items);
        return flag;
    }

    public int itemQuantity(){
        return size;
    }

    public int itemQuantity(String dishName){
        Item[] items = getItems();
        int flag = 0;
        for (int i = size-1; i>= 0; i--){
            if(items[i].getName().equals(dishName)){
                flag++;
            }
        }
        return flag;
    }

    public double costTotal(){
        Item[] items = getItems();
        double sum = 0;
        for(int i = 0; i<size; i++){
            sum += items[i].getCost();
        }
        return sum;
    }

    public String[] itemsNames(){
        Item[] items = getItems();
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
        Item[] temp = getItems();
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
