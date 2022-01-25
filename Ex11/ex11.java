import java.lang.Object;
public class ex11 {
  public static void main(String[] args){
    System.out.println("Matrix is");
    // System.out.println();
    int n = 6;
    int[][] matrix = new int[n][n];
    //初期化
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = 0;
      }
    }
    matrix[0][0] = 3;
    matrix[0][1] = -1;
    matrix[0][2] = -1;

    matrix[1][0] = -1;
    matrix[1][1] = 2;
    matrix[1][3] = -2;

    matrix[2][0] = -1;
    matrix[2][2] = 3;
    matrix[2][3] = -1;

    matrix[3][1] = -2;
    matrix[3][2] = -1;
    matrix[3][3] = 2;

    matrix[4][4] = 1;
    matrix[4][5] = -2;

    matrix[5][4] = -2;
    matrix[5][5] = 1;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(" " + String.format("%2d", matrix[j][i]) + " ");
      }
      System.out.println("");
    }
    int h = 3;
    System.out.println("Thus h = " + h);

    double[] A = new double[h * n];
    for (int i = 0; i < n; i++) {
      for (int j = 1; j <= h; j++) {
        if (i + 1 - j < 0) {
          A[(h * (i + 1)) - j] = 0;
          continue;
        }
        A[(h * (i + 1)) - j] = matrix[i + 1 - j][i];
      }
    }

    for (int i = 0; i < h * n; i++){
      if(i % 3 == 0){
        System.out.println("");
      }
      System.out.println(" A[" + i + "] = " + A[i]);
    }
    System.out.println("");

    double[] B = new double[6];
    B[0] = -2;
    B[1] = -5;
    B[2] = 4;
    B[3] = 1;
    B[4] = -7;
    B[5] = -4;

    LUband band = new LUband();
    band.decompose(A, n, h);
    band.solve(A, n, h, B);
    for (int i = 0; i < n; i++){
      System.out.println(" x[" + i + "] = " + String.format("%.4f", B[i]));
    }


    List<double> listA = new ArrayList<double>();
    int[] arrayh = new int[6];
    arrayh[0] = 1;
    arrayh[1] = 2;
    arrayh[2] = 3;
    arrayh[3] = 3;
    arrayh[4] = 1;
    arrayh[5] = 2;
    for (int i = 0; i < n; i++) {
      for (int j = 1; j <= arrayh[]; j++) {
        if (i + 1 - j < 0) {
          A[(h * (i + 1)) - j] = 0;
          continue;
        }
        A[(h * (i + 1)) - j] = matrix[i + 1 - j][i];
      }
    }
  }
}
