/**
*  Numerical Analysis. Exercise 03.
*/
public class ex03 {

  static double errorTolerance = 1e-7;

  public static void main(String[] args) {

    System.out.println();
    System.out.println("f(x) = x^3 -3*x^2 + 2*x - 0.2 = 0");

    new Bisection().solve();
    new Newton().solve();

  }

  static double f(double x) { return x*x*x -3*x*x + 2*x - 0.2; }
  static double fprime(double x) { return 3*x*x -6*x + 2; }
}

class Bisection {

  void solve() {
    int count = 1;
    double xL = 1.5, xR = 3, xcen, fxcen;
    double fL = new ex03().f(xL);
    double fR = new ex03().f(xR);
    double errorTolerance = new ex03().errorTolerance;

    System.out.println();
    System.out.println("Bisection method");

    while(Math.abs(xL - xR) > errorTolerance){
      xcen = (xL + xR) / 2;
      fxcen = new ex03().f(xcen);
      System.out.println("Iteration " + count + " : x = " + String.format("%.7f", xcen));
      if(fxcen == 0){
        break;
      }
      if((fL * fxcen) < 0){ //(fL*fxcen)<0となるのは、fLとfxcenが異符号の時。この時、本当の解はxLとxcenの間にあるので、xcenをxRに移して再計算。
        xR = xcen;
        fR = fxcen;
      }else{
        xL = xcen;
        fL = fxcen;
      }
      count++;
    }
  }

}

class Newton {

  void solve() {
    int count = 1;
    double errorTolerance = new ex03().errorTolerance;
    double f, fprime, xBefore = 0.0, xAfter = 3.0;
    System.out.println();
    System.out.println("Newton's method");
    while(Math.abs(xAfter - xBefore) > errorTolerance){
      xBefore = xAfter;
      f = new ex03().f(xBefore);
      fprime = new ex03().fprime(xBefore);
      xAfter = xBefore - (f / fprime); // x0での傾きf'(x0)は、f'(x0)=f(x0)/x0-x1。よって、x1=x0-f(x0)/f'(x0)。f'(x0)=f(x0)/x0-x1を使って新しいx1を見つけることで、本当の解に近づいていく。
      System.out.println("Iteration " + count + " : x = " + String.format("%.7f", xAfter));
      count++;
    }
  }

}
