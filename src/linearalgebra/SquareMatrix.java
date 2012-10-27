/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class SquareMatrix extends Matrix{
    private int size;
    
    public SquareMatrix(){
        
    }
    
    public SquareMatrix(int size){
        this.line = this.row = this.size = size;
    }
    
    public SquareMatrix(double[][] matrixArray, int size){
        this.matrixArray = matrixArray;
        this.line = this.row = this.size = size;
    }
    
    public SquareMatrix(double[][] matrixArray, int size, double accuracy){
        this.matrixArray = matrixArray;
        this.line = this.row = this.size = size;
        this.accuracy = accuracy;
    }
    
    public SquareMatrix(int size, double accuracy){
        this.line = this.row = this.size = size;
        this.accuracy = accuracy;
    }
    
    public SquareMatrix(double[][] matrixArray, int size, double accuracy, String name){
        this.matrixArray = matrixArray;
        this.line = this.row = this.size = size;
        this.accuracy = accuracy;
        this.name = name;
    }
    
    public SquareMatrix(SquareMatrix matrix){
        this.matrixArray = Matrix.matrixArrayCopy(matrix.getMatrixArray());
        this.line = this.row = this.size = matrix.getSize();
        this.accuracy = matrix.getAccuracy();
        this.name = matrix.getName();
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void setSize(int size){
        this.size = size;
        this.line = size;
        this.row = size;
    }
    
    public static SquareMatrix matrixToSquare(Matrix matrix){
        SquareMatrix squareMatrix = new SquareMatrix();
        
        squareMatrix.setMatrixArray(Matrix.matrixArrayCopy(matrix.getMatrixArray()));
        squareMatrix.setLine(matrix.getLine());
        squareMatrix.setRow(matrix.getRow());
        squareMatrix.setSize(matrix.getLine());
        squareMatrix.setAccuracy(matrix.getAccuracy());
        squareMatrix.setName(matrix.getName());
        return squareMatrix;
    }
    
    public static SquareMatrix creatUnitMatrix(int n){
       int i;
       SquareMatrix matrix = new SquareMatrix(n);
       
       for (i = 0;i < n;i ++)
               matrix.setElement(i, i, 1.0);
       return matrix;
   }
}
