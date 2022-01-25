import java.lang.Object;
import java.util.ArrayList;

public class ex11 {
  public static void main(String[] args){
    System.out.println("Matrix is");
    int n = 6;
    int[][] matrix = new int[n][n];
    // 初期化。
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
    // 右辺の初期化。
    double[] B = new double[n];
    B[0] = -2;
    B[1] = -5;
    B[2] = 4;
    B[3] = 1;
    B[4] = -7;
    B[5] = -4;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(" " + String.format("%2d", matrix[j][i]) + " ");
      }
      System.out.println("");
    }
    int h = 3; // 行列より、h=3。
    System.out.println("Thus h = " + h);
    System.out.println("");
    // ここまで初期化。
    double[] A = new double[h * n]; // band storageはhが固定長なので配列の長さがわかる。
    for (int i = 0; i < n; i++) {
      for (int j = 1; j <= h; j++) {
        if (i + 1 - j < 0) { // 行列の要素が足りないところは0を入れる。
          A[(h * (i + 1)) - j] = 0;
          continue;
        }
        A[(h * (i + 1)) - j] = matrix[i + 1 - j][i]; // 対角成分を先に入れて、その後その上の要素を入れる。+1しないとインデックスが-になるので注意。
      }
    }

    // for (int i = 0; i < h * n; i++){
    //   if(i % 3 == 0){
    //     System.out.println("");
    //   }
    //   System.out.println(" A[" + i + "] = " + A[i]);
    // }
    // System.out.println("");

    LUband band = new LUband();
    band.decompose(A, n, h);
    band.solve(A, n, h, B);
    for (int i = 0; i < n; i++){
      System.out.println(" x[" + i + "] = " + String.format("%.4f", B[i]));
    }


    ArrayList<Double> listA = new ArrayList<Double>(); // 配列の長さがわからないのでList型にaddしていく。
    int[] arrayh = new int[n]; // profile storageはhが可変長なのでそれぞれのhを配列で管理。
    arrayh[0] = 1;
    arrayh[1] = 2;
    arrayh[2] = 3;
    arrayh[3] = 3;
    arrayh[4] = 1;
    arrayh[5] = 2;
    int[] pcol = new int[n + 1]; // pcolは各列の要素の始めの位置と0が含まれるのでn+1。
    pcol[0] = 0; // 最初は0。

    for (int i = 0; i < n; i++) {
      pcol[i + 1] = pcol[i] + arrayh[i]; //ある要素の始めの位置は、前の要素の始めの位置+前の要素のh分先。
      if (arrayh[i] == 1) {
        listA.add((double)matrix[i][i]); // hが1の時は対角成分だけ入れる。
        continue;
      }
      for (int j = arrayh[i]; j > 0 ; j--) {
        listA.add((double)matrix[i + 1 - j][i]); // iは0からある対角成分までの長さなので、そこからhを引いてjを動かすことで下の要素をみることにした。
      }
    }

    Double[] ARRAYA = listA.toArray(new Double[listA.size()]); // List型をラッパークラスのDoubleの配列に変換。
    double[] arrayA = new double[listA.size()]; // ラッパークラスのDoubleだとダメなのでプリミティブ(基本)型のdoubleの配列を用意して、
    for (int i = 0; i < listA.size(); i++){
      arrayA[i] = ARRAYA[i]; // 代入。
    }

    // for (int i = 0; i < listA.size(); i++){
    //   System.out.print(" " + arrayA[i] + " ");
    // }
    // System.out.println("");
    // for (int i = 0; i < n + 1; i++) {
    //   System.out.print(" " + pcol[i] + " ");
    // }
    // System.out.println("");

    // 右辺の初期化
    B[0] = -2;
    B[1] = -5;
    B[2] = 4;
    B[3] = 1;
    B[4] = -7;
    B[5] = -4;

    System.out.println("");

    LUprofile profile = new LUprofile();
    profile.decompose(arrayA, pcol, n);
    profile.solve(arrayA, pcol, n, B);
    for (int i = 0; i < n; i++){
      System.out.println(" x[" + i + "] = " + String.format("%.4f", B[i]));
    }
  }
}
