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
    double dn = edge / h; // double型で計算して
    int n = (int)dn; // intにcast
    double[][] grid = new double[n + 1][n + 1]; // 縦横をn等分した時にできるグリッドの点は(n+1)*(n+1)個。これはu(k+1)の役割。
    double[][] before_grid = new double[n + 1][n + 1]; // これはu(k)の役割。
    double e = 1.0e-10, abs = 1;
    int flags = 0, iter = 0; // |u(k+1)-u(k)/u(k+1)|<eの時にflagsを追加。iterはループ回数。
    // 初期化
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        before_grid[i][j] = 0;
      }
    }

    while(flags != (n - 1) * (n - 1)){ // 縦横をn等分した時にできる境界線以外のグリッドの点は(n-1)*(n-1)。全ての点で前回の値との差がほぼ無くなったら終了。
      flags = 0;
      iter++;
      for (int i = 1; i < n; i++) {
        for (int j = 1; j < n; j++) {
          grid[i][j] = (before_grid[i+1][j] + grid[i-1][j] + before_grid[i][j+1] + grid[i][j-1] + 2 * Math.pow(h, 2)) / 4;
          abs = grid[i][j] - before_grid[i][j];
          abs = abs / grid[i][j];
          abs = Math.abs(abs);
          if (abs < e) {
            flags++;
          }
          before_grid[i][j] = grid[i][j];
        }
      }
    }

    // for (int i = 0; i < n + 1; i++) {
    //   for (int j = 0; j < n + 1; j++) {
    //     System.out.print(" " + String.format("%6.5f", grid[i][j]) + " ");
    //   }
    //   System.out.println("");
    // }

    System.out.println("Liebman method");
    System.out.println("n = " + n + " h = " + String.format("%.3f", h));
    System.out.println("iter = " + iter);
    double x = 0;
    for (int i = 0; i < n + 1; i++) {
      System.out.println("x = " + String.format("%4.2f", x) + " u = " + String.format("%7.5f", grid[i][n / 2]) + " ");
      x = x + h;
    }
    System.out.println();
  }

  void SOR (double h) {
    double overfac = 1.6;
    double dn = edge / h;
    int n = (int)dn;
    double[][] grid = new double[n + 1][n + 1];
    double[][] before_grid = new double[n + 1][n + 1];
    double e = 1.0e-10, abs = 1;
    int flags = 0, iter = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        before_grid[i][j] = 0;
      }
    }

    while(flags != (n - 1) * (n - 1)){
      flags = 0;
      iter++;
      for (int i = 1; i < n; i++) {
        for (int j = 1; j < n; j++) {
          grid[i][j] = (before_grid[i+1][j] + grid[i-1][j] + before_grid[i][j+1] + grid[i][j-1] - 4 * before_grid[i][j] + 2 * Math.pow(h, 2)) / 4;
          grid[i][j] = overfac * grid[i][j];
          grid[i][j] = before_grid[i][j] + grid[i][j];
          abs = grid[i][j] - before_grid[i][j];
          abs = abs / grid[i][j];
          abs = Math.abs(abs);
          if (abs < e) {
            flags++;
          }
          before_grid[i][j] = grid[i][j];
        }
      }
    }

    // for (int i = 0; i < n + 1; i++) {
    //   for (int j = 0; j < n + 1; j++) {
    //     System.out.print(" " + String.format("%6.5f", grid[i][j]) + " ");
    //   }
    //   System.out.println("");
    // }

    System.out.println("SOR method, overrelaxation factor = " + overfac);
    System.out.println("n = " + n + " h = " + String.format("%.3f", h));
    System.out.println("iter = " + iter);
    double x = 0;
    for (int i = 0; i < n + 1; i++) {
      System.out.println("x = " + String.format("%4.2f", x) + " u = " + String.format("%7.5f", grid[i][n / 2]) + " ");
      x = x + h;
    }
    System.out.println();
  }
}
