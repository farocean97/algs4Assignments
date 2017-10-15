import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if(args.length < 1) {
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);
        if(k < 0) {
             throw new IllegalArgumentException();
        }
            
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        
        while (true) {
            while(!StdIn.isEmpty()) {
                queue.enqueue(StdIn.readString());
            }
            if(queue.size() < k) {
                StdOut.println("Please type in more strings");
            } else {
                break;
            }
        }
        
        
         for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
        
    }
}