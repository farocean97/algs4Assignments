import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    // gridSize
    final private int gridSize;
    // grids
    private boolean[] grids;
    // wuf
    final private WeightedQuickUnionUF wuf;
    final private WeightedQuickUnionUF wufnb;
    
    private int m_numOfOpenSites;
    
    
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       if( n <= 0) {
           throw new java.lang.IllegalArgumentException();
       }
       this.gridSize = n;
       this.grids = new boolean[n*n+2];
       this.wuf = new WeightedQuickUnionUF(n*n+2);
       this.wufnb = new WeightedQuickUnionUF(n*n+1);
       m_numOfOpenSites =0;
   }
   
  
   private int indexofGrid(int i, int j) {
       if ( i > 0 && j > 0 && i <= this.gridSize && j <= this.gridSize) {
           return j + this.gridSize*( i - 1 );
       }else {
           return -1;
       }
   }
   
   private void connect(int i, int j) {
       this.wuf.union(i, j);
       if(i <= gridSize*gridSize && j <= gridSize*gridSize) {
           this.wufnb.union(i,j);
       }
   }
   
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       int index = this.indexofGrid(row, col); 
       int nbindex;
       if( index < 0) {
           throw new java.lang.IllegalArgumentException();
       }
       if( !this.grids[index]) {
           this.grids[index] = true;
           m_numOfOpenSites++;
       }
       //Connects with neighbor
       if(row == 1) {
           this.connect(index,0);
       }
       if(row == this.gridSize) {
           this.connect(index,this.gridSize*this.gridSize+1);
       }
       
       if((nbindex=this.indexofGrid(row-1,col)) > 0 && isOpen(nbindex)) {
           this.connect(index,nbindex);
       }
       
        if((nbindex=this.indexofGrid(row+1,col)) > 0 && isOpen(nbindex)) {
            this.connect(index,nbindex);
       }      
        if((nbindex=this.indexofGrid(row,col-1)) > 0 && isOpen(nbindex)) {
            this.connect(index,nbindex);
       }   
        if((nbindex=this.indexofGrid(row,col+1)) > 0 && isOpen(nbindex)) {
            this.connect(index,nbindex);
       } 
   }
   
   private boolean isOpen(int i) {
       return this.grids[i];
   }
   
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       int index = this.indexofGrid(row, col); 
       if( index < 0) {
           throw new java.lang.IllegalArgumentException();
       }
       return isOpen(index);
   }
   
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       int index = this.indexofGrid(row, col); 
       if( index < 0) {
           throw new java.lang.IllegalArgumentException();
       }  
       return this.wufnb.connected(index,0);
   }
   
   public int numberOfOpenSites()       // number of open sites
   {
       return m_numOfOpenSites;
   }
   public boolean percolates()              // does the system percolate?
   {
       return this.wuf.connected(0,this.gridSize*this.gridSize+1);
   }
   
   public static void main(String[] args)   // test client (optional)
   {
       Percolation per = new Percolation(10);
       int j = 5;
       for(int i= 1; i<= 10;i++){
           per.open(i,j);
        }
       if (per.percolates()) {
           System.out.println ("Yes");
       } else {
           System.out.println ("No"); 
       }
       
               
   }
}