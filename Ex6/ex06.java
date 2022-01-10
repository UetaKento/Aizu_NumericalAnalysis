public class ex06 {
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
    System.out.println("Trape " + calc.Trape(24));
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
  // double plus_sub(double x){
  //   return
  // }
  double Trape(int n){
    double sub = 1 / n, x1 = 0, x2 = 0, ans = 0, T;
    for (int i = 0; i < n; i++) {
      x1 = (x1 / 180) * Math.PI;
      x2 = ((x1 + sub) / 180) * Math.PI;
      T = ((Math.atan(x1) + Math.atan(x2)) * sub) / 2;
      ans = ans + T;
      x1 = x1 + sub;
    }
    return ans;
  }

}
