import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
   private Node<Item> first;
   private Node<Item> last;
   private int sizeQueue;
   private class Node<Item> {
       Item item;
       Node<Item> prev;
       Node<Item> next;
   }
    
   public Deque() {       // construct an empty deque
       first = null;
       last = null;
       sizeQueue = 0;
   }
   public boolean isEmpty() {                // is the deque empty?
       return (sizeQueue == 0);
   }
   public int size() {                       // return the number of items on the deque
       return sizeQueue;
   }
   public void addFirst(Item item) {          // add the item to the front
       if(item == null) {
           throw new java.lang.IllegalArgumentException();
       }
       Node<Item> newfirst = new Node<Item>();
       newfirst.item = item;
       newfirst.prev = null;
       newfirst.next = this.first;
       
       if(isEmpty()) {
           this.last = newfirst;
       }else {
           this.first.prev = newfirst;
       }
       this.first = newfirst;
       this.sizeQueue++;
   }
   
   public void addLast(Item item){           // add the item to the end
       if(item == null) {
           throw new java.lang.IllegalArgumentException();
       }
       Node<Item> newlast = new Node<Item>();
       newlast.item = item;
       newlast.prev = this.last;
       newlast.next = null;
       
       if(isEmpty()) {
           this.first = newlast;
       }else {
           this.last.next = newlast;
       }
       this.last = newlast;
       this.sizeQueue++;       
   }
   
   public Item removeFirst() {                // remove and return the item from the front
       Item item = null;
       if(isEmpty()) {
           throw new java.util.NoSuchElementException();
       } else if ( this.sizeQueue == 1) {
           item = this.first.item;
           this.first = null;
           this.last = null;
       } else {
           item = this.first.item;
           this.first = this.first.next;
           this.first.prev = null;
       }
       this.sizeQueue--;
       return item;
   }
   public Item removeLast() {                // remove and return the item from the end
       Item item = null;
       if(isEmpty()) {
           throw new java.util.NoSuchElementException();
       } else if ( this.sizeQueue == 1) {
           item = this.last.item;
           this.first = null;
           this.last = null;
       } else {
           item = this.last.item;
           this.last = this.last.prev;
           this.last.next = null;
       }
       this.sizeQueue--;
       return item;
   }
   public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
       return new ListIterator();
   }   
   
   private class ListIterator implements Iterator<Item> {
       private Node<Item> curNode = first;
       public boolean hasNext() {
           return curNode != null;
       }
       
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
       
       public Item next() {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           }
           Item item = curNode.item;
           curNode = curNode.next;
           return item;
       }
   }
       
   public static void main(String[] args) {   // unit testing (optional)
       Deque<Integer> queue = new Deque<Integer>();
       queue.addFirst(2);
       queue.addFirst(1);
       queue.addLast(3);
       queue.addLast(4);    
       StdOut.println("QueueSize = " + queue.size());
       for (int i : queue) {
           StdOut.println(i);
       }
       StdOut.println("Removed first value  = " + queue.removeFirst());   
       StdOut.println("Removed last value  = " + queue.removeLast());    
       for (int i : queue) {
           StdOut.println(i);
       }
   }
}