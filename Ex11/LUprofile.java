
/** Solution of the symmetric sparse equation system
  * by LDU method. The matrix is in profile format.<br>
  * Matrix in the symmetric profile format is represented by
  * two arrays: <br>
  * A[pcol[n]] - double array containing matrix columns of variable height,
  *  coefficients are listed from top to diagonal;<br>
  * pcol[n+1] - integer array containing pointers to top elements of columns.<br>
  * Parameter n is the number of equations.
  */
public class LUprofile {

/** UtDU decomposition for symmetric profile matrix.
  * Decomposed matrix replaces the initial matrix
  * @param A matrix by columns in profile format
  * @param pcol pointers to columns
  * @param n number of equations
  */
public void decompose (double[] A, int pcol[], int n) {

int i,j,k,ii,jj,ifirst,jfirst,m;
double w;

for(j=1; j<n; j++) {
    jfirst = j - (pcol[j+1]-pcol[j]) + 1;
    jj = pcol[j+1] - j - 1;
    for(i=jfirst; i<j; i++) {
        ifirst = i - (pcol[i+1]-pcol[i]) + 1;
        ii = pcol[i+1] - i - 1;
        w = 0;
        for(m=Math.max(jfirst,ifirst); m<i; m++) w += A[m+ii]*A[m+jj];
            A[i+jj] -= w;
  }
  for(i=jfirst; i<j; i++) {
      w = A[i+jj];
      A[i+jj] = w/A[pcol[i+1]-1];
      A[pcol[j+1]-1] -= w*A[i+jj];
  }
}
}

/** Reduction and backsubstitution for RHS.
  * @param A decomposed matrix by columns in profile format
  * @param pcol pointers to columns
  * @param n number of equations
  * @param x right-hand side(in)/solution vector(out)
  */
public void solve (double[] A, int pcol[], int n, double[] x) {

int i,j,jj,ifirst,jfirst,m;

for(j=1; j<n; j++) {
    jfirst = j - (pcol[j+1]-pcol[j]) + 1;
    jj = pcol[j+1] - j - 1;
    for(i=jfirst; i<j; i++) 
        x[j] -= A[i+jj]*x[i];
}

for(i=n-1; i>=0; i--) {
    x[i] /= A[pcol[i+1]-1];
    for(j=i+1; j<n; j++) {
        jfirst = j - (pcol[j+1]-pcol[j]) + 1;
        if(i>=jfirst) {
            jj = pcol[j+1] - j - 1;
            x[i] -= A[i+jj]*x[j];
        }
    }
}
}

}