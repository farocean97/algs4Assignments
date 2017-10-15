import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
   private int sizeQueue;
   private Item[] items;
   
   public RandomizedQueue() {                 // construct an empty randomized queue
       sizeQueue = 0;
       items = (Item[]) new Object[1];
   }
   public boolean isEmpty() {                // is the randomized queue empty?
       return (sizeQueue == 0);
   }
   public int size() {                        // return the number of items on the randomized queue
       return sizeQueue;
   }
   
   private void resize (int newsize) {
       Item[] newitems = (Item[]) new Object[newsize];
       for(int i = 0;i < sizeQueue;i++) {
           newitems[i] = items[i];
       }
       items = newitems;
           
   }
   public void enqueue(Item item) {          // add the item
       if (item == null) {
           throw new java.lang.IllegalArgumentException(); 
       }
       if(sizeQueue == items.length) {
           resize(2*sizeQueue);
       }
       items[sizeQueue] = item;
       sizeQueue++;
   }
   public Item dequeue() {                   // remove and return a random item
       if(isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int i= StdRandom.uniform(sizeQueue);
       Item item = items[i];
       sizeQueue--;
       items[i] = items[sizeQueue];
       items[sizeQueue]=null;
       if(sizeQueue == items.length/4 && sizeQueue > 0){
           resize(items.length/2);
       }
       return item;
    }
   
   public Item sample() {                     // return a random item (but do not remove it)
       if(isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int i= StdRandom.uniform(sizeQueue);
       return items[i];
    }
   
   public Iterator<Item> iterator() {       // return an independent iterator over items in random order
       return new ArrayIterator();
   }
   
   private class ArrayIterator implements Iterator<Item> {
       private int curidx = sizeQueue;
       public boolean hasNext() {
           return ( curidx > 0);
        }
       public Item next() {
           if(!hasNext()) {
               throw new java.util.NoSuchElementException();
           } 
            return items[--curidx];
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
   }
   
   public static void main(String[] args) {  // unit testing (optional)
       RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
       queue.enqueue(1);
       queue.enqueue(2); 
       queue.enqueue(3);
       queue.enqueue(4);     
       queue.enqueue(5);
       queue.enqueue(6); 
       queue.enqueue(7);
       queue.enqueue(8);   
       StdOut.println("dequeue element: " + queue.dequeue());
       for(Integer i : queue) {
           StdOut.println(i);
       }
       StdOut.println("sample element: " + queue.sample());
       for(Integer i : queue) {
           StdOut.println(i);
       }
   }
       
}