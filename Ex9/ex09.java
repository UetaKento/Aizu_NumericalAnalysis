/**
*  Numerical Analysis. Exercise 09
*  ODE
*/
public class ex09 {

  public static void main(String[] args) {

    System.out.println();
    System.out.println("dy/dx = xy, a=0, b=1, y(a) = 1");
    System.out.println();

    ex09 e = new ex09();
    e.Euler(0.1);
    e.RungeKutta(0.1);
    e.Euler(0.01);
    e.RungeKutta(0.01);

  }

  static double f(double x,double y) { return x*y; }

  static double a=0, b=1, ya = 1;

  void Euler(double h) {
    double yn_1 = 0, yn = 1.0, xn = 0;

    while(xn < 1){
      yn_1 = yn + h * f(xn, yn);
      yn = yn_1;
      xn = xn + h;    // xnが1.0になる時に0.999...となってしまいwhileがうまく回らないので
      xn = xn * 10;   // xnを10倍して9.999...にして
      Math.round(xn); // xnの小数点を四捨五入して10.0にして
      xn = xn / 10;   // xnを10で割る。
    }
    double Error = yn_1 - Math.exp(0.5 * xn * xn);
    System.out.println("Euler:       h = " + String.format("%.3f", h) + " x = " + String.format("%.3f", xn) +
    " y = " + String.format("%.8f", yn_1) + " Error = " + String.format("%.4e", Error));
  }


  void RungeKutta(double h) {
    double yn_1 = 0, yn = 1.0, xn = 0, k1, k2, k3, k4;

    while(xn < 1){
      k1 = h * f(xn, yn);
      k2 = h * f(xn + (h / 2), yn + (k1 / 2));
      k3 = h * f(xn + (h / 2), yn + (k2 / 2));
      k4 = h * f(xn + h, yn + k3);
      yn_1 = yn + ((k1 + 2 * k2 + 2 * k3 +k4) / 6);
      yn = yn_1;
      xn = xn + h;    // xnが1.0になる時に0.999...となってしまいwhileがうまく回らないので
      xn = xn * 10;   // xnを10倍して9.999...にして
      Math.round(xn); // xnの小数点を四捨五入して10.0にして
      xn = xn / 10;   // xnを10で割る。
    }
    double Error = yn_1 - Math.exp(0.5 * xn * xn);
    System.out.println("Runge-Kutta: h = " + String.format("%.3f", h) + " x = " + String.format("%.3f", xn) +
    " y = " + String.format("%.8f", yn_1) + " Error = " + String.format("%.4e", Error));
  }

}
