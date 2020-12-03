package Restaurant;

public class ListNode {
    public ListNode next;
    public ListNode previous;
    public Item value;

    ListNode(ListNode next, ListNode previous, Item value){
        this.next = next;
        this.previous = previous;
        this.value = value;
    }

    ListNode(Item value){
        this(null, null, value);
    }
}
