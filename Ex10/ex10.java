public class ex10 {
  public static double edge = 1;
  public static void main(String[] args){
    System.out.println();
    System.out.println("d^2u/dx^2 + d^2u/dy^2 + 2 = 0, edge length=1");
    System.out.println();

    ex10 e = new ex10();
    e.Liebman(0.1);
    // e.SOR(0.1);
    // e.Liebman(0.05);
    // e.SOR(0.05);

  }

  void Liebman (double h) {
    int iter = 0;
    double dn = edge / h;
    int n = (int)dn;
    double[][] grid = new double[n][n];
    double[][] before_grid = new double[n][n];
    double k = 0, kt1, e = 1.0e-10, abs = 1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = 0;
      }
    }

    while(abs > e){
      for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < n - 1; j++) {
          grid[i][j] = (grid[i+1][j] + grid[i-1][j] + grid[i][j+1] + grid[i][j-1] + 2 * Math.pow(h, 2)) / 4;
        }
      }
      kt1 = grid[n - 2][n - 2];
      abs = kt1 - k;
      abs = abs / kt1;
      abs = Math.abs(abs);
      System.out.println("abs = " + abs);
      k = kt1;
    }

    //初期化
    // for (int i = 0; i < n + 2; i++) {
    //   grid[i][0] = 0;
    //   grid[i][n + 1] = 0;
    //   if (i == 0 || i == n + 1) {
    //     for (int j = 0; j < n + 2; j++) {
    //       grid[i][j] = 0;
    //     }
    //   }
    // }
    System.out.println("Liebman method");
    System.out.println("n = " + n + " h = " + String.format("%.3f", h));

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(" " + String.format("%6.5f", grid[i][j]) + " ");
      }
      System.out.println("");
    }

  }

  void SOR (double h) {
    double overfac = 1.6;
    int iter = 0;
    System.out.println("SOR method, overrelaxation factor = " + overfac);
    System.out.println("n = " + edge / h + " h = " + String.format("%.3f", h));
  }
}
