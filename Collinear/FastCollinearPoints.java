import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> lstSegments;
    private final ArrayList<Point> lstStartPoints;
    public FastCollinearPoints(Point[] points)  {   // finds all line segments containing 4 or more points
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        lstSegments = new ArrayList<LineSegment>();
        lstStartPoints = new ArrayList<Point>();
        Arrays.sort(points);
        Point[] pointscp = Arrays.copyOf(points, points.length);
        ArrayList<Point> lstpoints = new ArrayList<Point>();
        for (Point pt : points) {
            Arrays.sort(pointscp, pt.slopeOrder());
            double oldslope = Double.NEGATIVE_INFINITY;
            int count = 0;
            for(Point ptcp : pointscp) {
                double slope = ptcp.slopeTo(pt) ;
                if (slope == oldslope) {
                    count++;
                    lstpoints.add(ptcp);
                }else {
                    if(count > 2) {
                        lstpoints.add(pt);
                        Collections.sort(lstpoints);
                        LineSegment newline = new LineSegment(lstpoints.get(0),lstpoints.get(lstpoints.size()-1));
                        if(lstSegments.size()> 0) {
                            if( pt.compareTo(lstpoints.get(0)) == 0 || lstStartPoints.get(lstStartPoints.size()-1).compareTo(lstpoints.get(0))<0) {
                                lstSegments.add(newline);
                                lstStartPoints.add(lstpoints.get(0));
                            }
                        } else {
                            lstSegments.add(newline);
                            lstStartPoints.add(lstpoints.get(0));
                        }
                    }
                    count =1;
                    oldslope = ptcp.slopeTo(pt);
                    lstpoints.clear();
                    lstpoints.add(ptcp);
                }
                
            }
            //handling largest slope
            if(count > 2) {
                lstpoints.add(pt);
                Collections.sort(lstpoints);
                LineSegment newline = new LineSegment(lstpoints.get(0),lstpoints.get(lstpoints.size()-1));
                if(lstSegments.size()> 0) {
                    Point p1 = lstStartPoints.get(lstStartPoints.size()-1);
                    Point p2 = lstpoints.get(0);
                    int ic = p1.compareTo(p2);
                    if( pt.compareTo(lstpoints.get(0)) == 0 || lstStartPoints.get(lstStartPoints.size()-1).compareTo(lstpoints.get(0))<0) {
                        lstSegments.add(newline);
                        lstStartPoints.add(lstpoints.get(0));
                    }
                } else {
                    lstSegments.add(newline);
                    lstStartPoints.add(lstpoints.get(0));
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
       FastCollinearPoints fcq = new FastCollinearPoints(points);
       StdOut.println(fcq.numberOfSegments());
       for(LineSegment ls : fcq.segments()){
           StdOut.println( ls.toString());
       }
        
       //p0.drawTo(p2);
       
   }
}