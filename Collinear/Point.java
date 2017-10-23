import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {                         // constructs the point (x, y)
        if(x < 0|| y < 0 || x > 32767 || y > 32767) {
            throw new java.lang.IllegalArgumentException();
        } else {
            this.x = x;
            this.y = y;
        }
    }
    
    public void draw() {                               // draws this point
        StdDraw.point(x,y);
    }
    public void drawTo(Point that) {                  // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {                          // string representation
        return "(" + x + ", " + y +")";
    }
    
    public int compareTo(Point that) {    // compare two points by y-coordinates, breaking ties by x-coordinates
        if(this.y < that.y) return -1;
        if(this.y > that.y) return 1;
        if(this.x < that.x) return -1;
        if(this.x > that.x) return 1;
        return 0;
    }
    public double slopeTo(Point that) {      // the slope between this point and that point
        int dx = that.x - this.x;
        int dy = that.y - this.y;
        if(dx == 0) {
            if( dy == 0 ) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } 
        return (double) dy/dx;
    }
    public Comparator<Point> slopeOrder() {             // compare two points by slopes they make with this point
        return new BySlope();
    }
    private class BySlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double dslope = slopeTo(p1)- slopeTo(p2);
            if(dslope < 0) return -1;
            if(dslope > 0) return 1;
            return 0;
        }
    }
    
    public static void main(String[] args) {
        Point p0 = new Point(0,0);
        Point p1 = new Point(0,1);
        Point p2 = new Point(1,1);
        Point p3 = new Point(2,2);
        Point p4 = new Point(3,4);
        StdOut.println(p0.slopeTo(p1));
        StdOut.println(p0.slopeTo(p2));
        StdOut.println(p0.slopeTo(p3));
        StdOut.println(p0.slopeTo(p4));  
        StdOut.println(p1.slopeTo(p2));    
        StdOut.println(p0.compareTo(p1));
        StdOut.println(p1.compareTo(p2));
        StdOut.println(p2.compareTo(p3));
        StdOut.println(p3.compareTo(p4)); 
        //p0.drawTo(p2);
        
    }
    
    
}