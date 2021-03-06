public class ex08 {
  public static void main(String[] args) {
    Calculate calc = new Calculate();
    double x, print_x;
    double h = 0.025 * Math.PI;
    System.out.println("Forward difference");
    for (int i = 0; i < 6; i++){
      x = i;
      print_x = x / 10;
      x = print_x * Math.PI;
      System.out.println("x = " + print_x +" PI "
      + " Deriv = " + String.format("%.6f", calc.Derivative_a(x, h))
      // + " Error = " + String.format("%.6f", calc.Derivative_a(x, h) - Math.cos(x)));
      + " Error = " + String.format("%.6f", calc.Error_a(x, h)));
    }
    System.out.println("\n");
    System.out.println("Central difference");
    for (int i = 0; i < 6; i++){
      x = i;
      print_x = x / 10;
      x = print_x * Math.PI;
      System.out.println("x = " + print_x +" PI "
      + " Deriv = " + String.format("%.6f", calc.Derivative_b(x, h))
      // + "  Error = " + String.format("%.6f", calc.Derivative_b(x, h) - Math.cos(x)));
      + " Error = " + String.format("%.6f", calc.Error_b(x, h)));
    }
    System.out.println("\n");
    System.out.println("Numerical integration");
    System.out.println("I = Integral(1/(x^2+1)), limits: [0,1]");
    System.out.println("\n");
    System.out.println("Trapezoidal rule, nsub = 24");
    System.out.println("I = " + String.format("%.12f", calc.Trape(24))
    + " Error = " + String.format("%.4e", calc.Error_t(24))
    + " R = " + String.format("%.2e", calc.Relative_t(24) * 100) + " %");
    System.out.println("\n");
    System.out.println("Simpson 1/3 rule, nsub = 24");
    System.out.println("I = " + String.format("%.12f", calc.Simpson(24))
    + " Error = " + String.format("%.4e", calc.Error_s(24))
    + " R = " + String.format("%.2e", calc.Relative_s(24) * 100) + " %");
  }
}

class Calculate {
  double Derivative_a(double x, double h) {
    return (Math.sin(x + h) - Math.sin(x)) / h;
  }
  double Error_a(double x, double h) {
    return Derivative_a(x,h) - Math.cos(x);
  }

  double Derivative_b(double x, double h) {
    return (Math.sin(x + h) - Math.sin(x - h)) / (2*h);
  }

  double Error_b(double x, double h) {
    return Derivative_b(x,h) - Math.cos(x);
  }

  double Trape(double n){
    Func func = new Func();
    double sub = 1 / n, x1 = 0, x2 = 0, ans = 0;
    for (int i = 0; i < n; i++) {
      x2 = x1 + sub;
      ans = ((func.F(x1) + func.F(x2)) * sub / 2) + ans;
      x1 = x1 + sub;
    }
    return ans;
  }

  double Error_t(double n) {
    return  Trape(n) - Math.atan(1);
  }

  double Relative_t(double n) {
    return  Error_t(n) / Math.atan(1);
    // return  (Trape(n) - Math.atan(1))/ Math.atan(1);
  }

  double Simpson(double n){
    Func func = new Func();
    double sub = 1 / n, x1 = 0, x2 = 0, x3 = 0, ans = 0;
    for (int i = 0; i < n / 2; i++) {
      x2 = x1 + sub;
      x3 = x2 + sub;
      // System.out.println("x1 = " + x1 + " x2 = " + x2 + " x3 = " + x3);
      ans = ((func.F(x1) + 4 * func.F(x2) + func.F(x3)) * sub / 3) + ans;
      x1 = x3;
    }
    return ans;
  }

  double Error_s(double n) {
    return  Simpson(n) - Math.atan(1);
  }

  double Relative_s(double n) {
    return  Error_s(n) / Math.atan(1);
  }
}

class Func{
  double F(double x) {
    double x2t1 = x * x + 1;
    return 1 / x2t1;
  }
}
