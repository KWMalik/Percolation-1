/**
 * Name: Raj Sahae
 * Date: 8/12/2012
 * Week 1 Assignment: Percolation
 * 
 * To use this class, run on the command line, for example:
 * java PercolationStats 200 100
 */

public class PercolationStats {

  private double[] thresholds;    // Array to contain the Percolation thresholds
  private Percolation p;          // Used to hold the current percolation

  /**
   * Constructor for PercolationStats
   * Perform T independent computational experiments on an NxN grid
   */
  public PercolationStats(int N, int T) {
    thresholds = new double[T];
    Integer count = 0;
    Out out = new Out();

    for (int i = 0; i < T; i++) {
      out.printf("Working on percolation %d", i);
      p = new Percolation(N);
      while (!p.percolates()) {
        int x = StdRandom.uniform(N) + 1;
        int y = StdRandom.uniform(N) + 1;
        //out.printf("X and Y are: %d, %d\n", x, y);
        if (!p.isOpen(x, y)) {
          p.open(x, y);
          count++;
        }
      } 
      //out.println("Outside the while");
      thresholds[i] = count.doubleValue() / N;
    }
    out.close();
  }

  /**
   * Returns the sample mean of percolation threshold
   */
  public double mean() {
    return StdStats.mean(thresholds);
  }

  /**
   * Returns the sample standard deviation of the percolation threshold
   */
  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  /**
   * Test Client
   */
  public static void main(String[] args) {
    Out out = new Out();
    Integer N = new Integer(args[0]);
    Integer T = new Integer(args[1]);

    PercolationStats ps = new PercolationStats(N, T);

    double mean = ps.mean();
    double stddev = ps.stddev();
    double lowConf = mean - (1.96 * stddev / Math.sqrt(T));
    double highConf = mean + (1.96 * stddev / Math.sqrt(T));

    out.printf("mean                    = %f\n", mean);
    out.printf("stddev                  = %f\n", stddev);
    out.printf("95% confidence interval = %f, %f\n", lowConf, highConf);
    out.close();
  }
}
