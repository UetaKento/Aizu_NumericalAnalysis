
/** Solution of the symmetric band equation system
 * by LDU method. The matrix is in column format. <br>
 * Column format: upper symmetric part of the matrix is
 * stored by columns. Coefficients in columns start from the
 * top and end by the diagonal. Zeros added to the top of 
 * first (h-1) columns in order to have constant column height h
 * (h is matrix bandwidth = column height).
 */
public class LUband {
    
    /** UtDU decomposition for symmetric band matrix.
     * @param a matrix by columns of constant height
     * @param n number of equations
     * @param h bandwidth
     */
    public void decompose (double[] a, int n, int h) {
	
	int i,j,k,h1=h-1;
	double w;
	
	for(j=1; j<n; j++) {
	    for(i=Math.max(j-h1,0); i<j; i++) {
		for(k=Math.max(j-h1,0); k<i; k++) {
		    a[i+h1*(j+1)] -= a[k+h1*(i+1)] * a[k+h1*(j+1)];
		}
	    }
	    for(i=Math.max(j-h1,0); i<j; i++) {
		w = a[i+h1*(j+1)];
		a[i+h1*(j+1)] /= a[i+h1*(i+1)];
		a[j+h1*(j+1)] -= a[i+h1*(j+1)]*w;
	    }
	}
    }
    
    /** Reduction and backsubstitution for RHS.
     * @param a decomposed matrix by columns of constant height
     * @param n number of equations
     * @param h bandwidth
     * @param b right-hand side(in)/solution vector(out)
     */
    public void solve (double[] a, int n, int h, double[] b) {
	
	int i,j,h1=h-1 ;
	
	for(j=1; j<n; j++) {
	    for(i=Math.max(j-h1,0); i<j; i++) b[j] -= a[i+h1*(j+1)]*b[i];
	}
	for(j=0; j<n; j++) {
	    b[j] /= a[j+h1*(j+1)];
	}
	for(j=n-1; j>=0; j--) {
	    for(i=Math.max(j-h1,0); i<j; i++) {
		b[i] -= a[i+h1*(j+1)] * b[j];
	    }
	}
    }
    
}
