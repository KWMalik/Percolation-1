/**
 * Name: Raj Sahae
 * Date: 8/12/2012
 * Week 1 Assignment: Percolation
 * 
 * To use this class, instantiate a new Percolation square grid with NxN sides
 * Use the Percolation.open method to open a certain number of grid sites
 * and then test whether or not the grid will percolate with the
 * Percolation.percolates method.
 */

public class Percolation {
  // reference indices with "i" and "j", where i and j are are between 1 and N
  // (1, 1) is the upper left side of the grid
  
  private int siteSize;   // The size of one side of the grid (N)
  private UF grid;    // Union find data structure representing the grid
  private boolean[][] sites;  // data structure for tracking open/closed sites
  private int gridSize; // Size of the Union Find collection

  /**
   * Create N-by-N grid, with all sites blocked.
   */
  public Percolation(int N) {
    // Constructor should take N^2 time
    siteSize = N;
    gridSize = N*N+2;
    sites = new boolean[N][N];
    grid = new UF(gridSize);
    for (int i = 1; i <= N; i++) {
      grid.union(0, i);
      grid.union(gridSize-1, gridSize - i - 1);
    }
  }

  /**
   * Open site (row i, column j) if it is not already open.
   */
  public void open(int i, int j) {
    validateArgs(i, j);
    if (!getSite(i, j)) {
      setSite(i, j, true);

      // Union to all open sites around us
      if (i != 1 && getSite(i-1, j)) { // We are not at the top
        grid.union(getIndexFromArgs(i, j), getIndexFromArgs(i-1, j));
      } 
      else if (i != siteSize && getSite(i+1, j)) { // Not at bottom
        grid.union(getIndexFromArgs(i, j), getIndexFromArgs(i+1, j));
      } 
      else if (j != 1 && getSite(i, j-1)) { // Not on the left side
        grid.union(getIndexFromArgs(i, j), getIndexFromArgs(i, j-1));
      } 
      else if (j != siteSize && getSite(i, j+1)) { // Not on the right
        grid.union(getIndexFromArgs(i, j), getIndexFromArgs(i, j+1));
      }
    }
  }
      
  /**
   * Returns true if the site (row i, column j) is open, false otherwise.
   */
  public boolean isOpen(int i, int j) {
    validateArgs(i, j);
    return getSite(i, j);
  }

  /**
   * Returns true if the site (row i, column j) is open and full,
   * where "full" means the site is connected to the top row.
   * Return false otherwise.
   */
  public boolean isFull(int i, int j) {
    validateArgs(i, j);
    return isOpen(i, j) && grid.connected(0, getIndexFromArgs(i, j));
  }

  /**
   * Returns true if the system percolates from top to bottom, false otherwise.
   */
  public boolean percolates() {
    return grid.connected(0, gridSize-1);
  }

  private void validateArgs(int i, int j) {
    if (i > siteSize || j > siteSize || i < 1 || j < 1) {
      String message = String.format("Indices [%d, %d] are outside bounds [%d]",
        i, j, siteSize);
      throw new IndexOutOfBoundsException(message);
    }
  }

  private int getIndexFromArgs(int i, int j) {
    return siteSize*(i-1) + j;
  }

  private boolean getSite(int i, int j) { return sites[i-1][j-1]; }
  private void setSite(int i, int j, boolean val) { sites[i-1][j-1] = val; }
}
