/**
 *  Numerical Analysis. Exercise 02.
 */
public class ex02 {
    public static void main(String[] args) {
	new QuadEquation().evaluate();
	new Cancellation().evaluate();
    }
}

class QuadEquation {
    void evaluate() {
      float f_a = 1, f_b = -8000, f_c = 1;
      double d_a = 1.0, d_b = -8000.0, d_c = 1.0;
      float f_b2 = f_b * f_b, f_four_ac = 4 * f_a * f_c;
      double d_b2 = d_b * d_b, d_four_ac = 4 * d_a * d_c;
      float f_x1 = 0, f_x2 = 0;
      double d_x1 = 0.0, d_x2 = 0.0;
      System.out.println("Task 1. Quadratic equation ax^2+bx+c=0");
      System.out.println("--------------------------------------");
      System.out.println("a = " + d_a + " b = " + d_b + " c = " + d_c);


      d_x1 = -d_b + Math.sqrt(d_b2 - d_four_ac);
      d_x2 = -d_b - Math.sqrt(d_b2 - d_four_ac);

      d_x1 = d_x1 / (2 * d_a);
      d_x2 = d_x2 / (2 * d_a);


      f_x1 = (float)Math.sqrt(f_b2 - f_four_ac);
      f_x1 = -f_b + f_x1;
      f_x1 = f_x1 / (2 * f_a);

      f_x2 = (float)Math.sqrt(f_b2 - f_four_ac);
      f_x2 = -f_b - f_x2;
      f_x2 = f_x2 / (2 * f_a);

      System.out.println("Single precision  x1 = " + f_x1 + "   x2 = " + String.format("%.7e", f_x2));
      System.out.println("Double precision  x1 = " + d_x1 + "   x2 = " + String.format("%.16e", d_x2));
    }
}

class Cancellation {
    void evaluate(){
      System.out.println();
      System.out.println("Task 2. Cancellation");
      System.out.println("--------------------");

      double x_12 = 1.0e12, x_9 = 1.0e9, x_44 = 44.999999999999;
      double radi = (x_44 / 180) * Math.PI;
      double log_ans, e_ans, sqrt_ans, x_ans, c2s2_ans, c2_ans;

      log_ans = Math.log(x_12 + 1) - Math.log(x_12);
      e_ans = Math.log(1 + 1/x_12);
      sqrt_ans = Math.sqrt(x_9 * x_9 + 1) - x_9;
      x_ans = Math.sqrt(x_9 * x_9 + 1) + x_9;
      x_ans = 1 / x_ans;
      double c2 = Math.cos(radi) * Math.cos(radi);
      double s2 = Math.sin(radi) * Math.sin(radi);
      c2s2_ans = c2 - s2;
      c2_ans = Math.cos(2 * radi);
      // c2s2_ans = Math.pow(Math.cos(x_44), 2) - Math.pow(Math.sin(x_44), 2);
      System.out.println("ln(x+1)-ln(x),     x = " + x_12 + " : " + log_ans);
      System.out.println("ln(1+(1/x)),       x = " + x_12 + " : " + e_ans);
      System.out.println("sqrt(x^2+1)-x,     x = " + x_9 + " : " + sqrt_ans);
      System.out.println("1/sqrt(x^2+1)+x,   x = " + x_9 + " : " + x_ans);
      // System.out.println("cos^2(x), x = " + x_44 + " : " + String.format("%.16e", c2));
      // System.out.println("sin^2(x), x = " + x_44 + " : " + String.format("%.16e", s2));
      System.out.println("cos^2(x)-sin^2(x), x = " + x_44 + " : " + String.format("%.16e", c2s2_ans));
      System.out.println("cos2(x),           x = " + x_44 + " : " + String.format("%.16e", c2_ans));
    }
}
