public class ex10 {
  public static double edge = 1;
  public static void main(String[] args){
    System.out.println();
    System.out.println("d^2u/dx^2 + d^2u/dy^2 + 2 = 0, edge length=1");
    System.out.println();

    ex10 e = new ex10();
    e.Liebman(0.1);
    e.SOR(0.1);
    e.Liebman(0.05);
    e.SOR(0.05);

  }

  void Liebman (double h) {
    int iter = 0;
    double dn = edge / h;
    int n = (int)dn;
    double[][] grid = new double[n + 2][n + 2];

    // for (int i = 0; i < n + 2; i++) {
    //   for (int j = 0; j < n + 2; j++) {
    //     grid[i][j] = 1;
    //   }
    // }

    //初期化
    for (int i = 0; i < n + 2; i++) {
      grid[i][0] = 0;
      grid[i][n + 1] = 0;
      if (i == 0 || i == n + 1) {
        for (int j = 0; j < n + 2; j++) {
          grid[i][j] = 0;
        }
      }
    }
    System.out.println("Liebman method");
    System.out.println("n = " + n + " h = " + String.format("%.3f", h));

    // for (int i = 0; i < n + 2; i++) {
    //   for (int j = 0; j < n + 2; j++) {
    //     System.out.print(" " + grid[j][i] + " ");
    //   }
    //   System.out.println("");
    // }

  }

  void SOR (double h) {
    double overfac = 1.6;
    int iter = 0;
    //初期化
    for (int i = 0; i < n + 2; i++) {
      grid[i][0] = 0;
      grid[i][n + 1] = 0;
      if (i == 0 || i == n + 1) {
        for (int j = 0; j < n + 2; j++) {
          grid[i][j] = 0;
        }
      }
    }
    System.out.println("SOR method, overrelaxation factor = " + overfac);
    System.out.println("n = " + edge / h + " h = " + String.format("%.3f", h));
  }
}
