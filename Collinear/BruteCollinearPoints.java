import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
   private final ArrayList<LineSegment> lstSegments;
   
   public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
       if (points == null) {
           throw new java.lang.IllegalArgumentException();
       }
       Arrays.sort(points);
       lstSegments = new ArrayList<LineSegment>();
       for(int i = 0; i< points.length-3; i++) {
           for( int j = i+1; j < points.length-2;j++) {
               for (int k = j+1; k < points.length -1; k++) {
                   for (int l = k+1; l < points.length; l++) {
                       double s1 = points[i].slopeTo(points[j]);
                       if (s1== points[i].slopeTo(points[k]) && s1 == points[i].slopeTo(points[l])) {
                           lstSegments.add(new LineSegment(points[i], points[l]));
                       }
                   }
               }
           }
       }
       
       
   }
   
   public  int numberOfSegments() {        // the number of line segments
       return this.lstSegments.size();
   }
   public LineSegment[] segments() {               // the line segments
       return lstSegments.toArray(new LineSegment[this.numberOfSegments()]);
   }
   
   public static void main(String[] args) {
       Point[] points = new Point[] { new Point (0, 0),
           new Point (3,3), 
           new Point (3,2),
           new Point (2,3),
           new Point (0,4),
           new Point (2,2),
           new Point (1,3),
           new Point (1,6),
           new Point (1,1),
           new Point (3,1),
       };
       BruteCollinearPoints bcq = new BruteCollinearPoints(points);
       StdOut.println(bcq.numberOfSegments());
       for(LineSegment ls : bcq.segments()){
           StdOut.println( ls.toString());
       }
        
       //p0.drawTo(p2);
       
   }
}