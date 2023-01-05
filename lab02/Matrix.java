package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	public Matrix(){}
	
	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim){
		/*
		* TODO: write a constructor that would create a matrix
		* of correct size and initialize it to 0. 
		*/
		this.numRows = rowDim;
		this.numColumns = colDim;
		this.data = new int[rowDim][colDim];
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
		this.numRows = d.length;
		this.numColumns = d[0].length;
		int[][] mat = new int[d.length][d[0].length];
		for(int i = 0; i < d.length; i++) {
			for(int j = 0; j < d[0].length; j++) {
				mat[i][j] = d[i][j];
			}
		}
		this.data = mat;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified in the instruction for M1 - anything else IS NOT acceptable
		 */
		String mat = "\n";
		for(int i = 0; i < this.numRows; i++) {
			for(int j = 0; j < this.numColumns; j++) {
				mat += this.data[i][j];
				mat += " ";
		    }
			mat += "\n";
		}
		return mat; // placeholder		
	}
	
	// *** you will implement the rest of the methods for your assignment
	// *** don't touch them before finishing the lab portion
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		for(int i = 0; i < m.numRows; i++) {
			for(int j = 0; j < m.numColumns; j++) {
				if(m.data[i][j] != this.data[i][j]) {
					return false;
				}
			}
		}
		return true; // placeholder
	}

	public Matrix mult(Matrix m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		
		if(this.numColumns == m.numRows) {
			Matrix mom = new Matrix(this.numRows, m.numColumns);
			
			for(int i = 0; i < this.numRows; i++) {
				for(int j = 0; j < m.numColumns; j++) {
					for(int k = 0; k < this.numColumns; k++) {
						mom.data[i][j] += this.data[i][k] * m.data[k][j];
					}
				}
			}
			return mom;
		}
		else {
			return null;
		}
	}
	
	public Matrix add(Matrix m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
		if((m.numColumns == this.numColumns) && (m.numRows == this.numRows)) {
			Matrix mat = new Matrix(this.numRows, this.numColumns);
			for(int i = 0; i < this.numRows; i++) {
				for(int j = 0; j < this.numColumns; j++) {
					mat.data[i][j] = m.data[i][j] + this.data[i][j];
				}
			}
			return mat;
		}
		else {
			return null;
		}
	}
    
    public Matrix transpose()
    {
        /*
         * TODO: replace the below return statement with the correct code,
         */
        Matrix m = new Matrix(this.numColumns, this.numRows);
        int[][] dad = new int[this.numColumns][this.numRows];
        for(int i = 0; i < this.numColumns; i++) {
        	for(int j = 0; j < this.numRows; j++) {
        		dad[i][j] = this.data[j][i];
        	}
        }
        m = new Matrix(dad);
        return m;
    }
}
