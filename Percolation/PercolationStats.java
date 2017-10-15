import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   private double[] ratios;
   private int ntrials;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       if(n < 1 || trials < 1) {
           throw new IllegalArgumentException();
        }
       this.ntrials = trials;
       this.ratios = new double[trials];
       for(int iter = 0;iter<trials; iter++) {
           Percolation per = new Percolation(n);
           while(!per.percolates()) {
               int row = StdRandom.uniform(1,n+1);
               int col = StdRandom.uniform(1,n+1);
               per.open(row,col);
           }
           this.ratios[iter] = (double) per.numberOfOpenSites()/(n*n);
       }
  
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(this.ratios);
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(this.ratios);
   }       
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(this.ntrials));
   }       
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(this.ntrials));
   }       

   public static void main(String[] args)        // test client (described below)
   {
       int n = 1000;
       int trials = 100;
       PercolationStats per = new PercolationStats(n,trials);

        String confidence = per.confidenceLo() + ", " + per.confidenceHi();
        StdOut.println("mean                    = " + per.mean());
        StdOut.println("stddev                  = " + per.stddev());
        StdOut.println("95% confidence interval = " + confidence);    
   }
}